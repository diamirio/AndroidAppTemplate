package com.tailoredapps.androidapptemplate.base.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Use in [FragmentActivity].
 *
 * If the secondary framework constructor is not used and the property would be accessed before
 * [FragmentActivity.setContentView] an [IllegalStateException] is thrown.
 *
 *
 * Example with secondary framework constructor (preferred):
 *
 * ```
 * class MainActivity : FragmentActivity(R.layout.activity_main) { // layout inflation happens in framework
 *
 *     private val binding by viewBinding(ActivityMainBinding::bind) // binding via extension
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         binding.button.setOnClickListener { ... }
 *     }
 * }
 * ```
 *
 * Example with setContentView:
 *
 * ```
 * class MainActivity : FragmentActivity() {
 *
 *     private val binding by viewBinding(ActivityMainBinding::bind) // binding via extension
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         setContentView(R.layout.activity_main) // layout inflation happens via setContentView
 *         binding.button.setOnClickListener { ... }
 *     }
 * }
 * ```
 */
fun <Binding : ViewBinding> FragmentActivity.viewBinding(
    binder: (View) -> Binding
): ReadOnlyProperty<FragmentActivity, Binding> = object : ReadOnlyProperty<FragmentActivity, Binding> {

    private var binding: Binding? = null

    override fun getValue(thisRef: FragmentActivity, property: KProperty<*>): Binding {
        val binding = binding
        if (binding != null) return binding
        return binder(requireContentView()).also { this.binding = it }
    }
}

internal fun FragmentActivity.requireContentView(): View {
    return checkNotNull(findViewById<ViewGroup>(android.R.id.content).getChildAt(0)) {
        "Content layout missing. Set it either with secondary framework constructor or setContentView."
    }
}

/**
 * Use in [Fragment].
 * The binding can be safely accessed between [Fragment.onViewCreated] and [Fragment.onDestroyView]. The reference
 * to the [ViewBinding] will automatically be cleaned up after [Fragment.onDestroyView].
 *
 * If the property would be accessed before [Fragment.onViewCreated] or after [Fragment.onDestroyView], an
 * [IllegalStateException] is thrown.
 *
 *
 * Example with secondary framework constructor (preferred):
 *
 * ```
 * class SomeFragment : Fragment(R.layout.fragment_some) { // layout inflation happens in framework
 *
 *     private val binding by viewBinding(FragmentSomeBinding::bind) // binding via extension
 *
 *     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *         super.onViewCreated(view, savedInstanceState)
 *         binding.button.setOnClickListener { ... }
 *     }
 * }
 * ```
 *
 * Example with onCreateView:
 *
 * ```
 * class SomeFragment : Fragment() {
 *
 *     private val binding by viewBinding(FragmentSomeBinding::bind) // binding via extension
 *
 *     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
 *         // layout inflation happens via onCreateView
 *         return inflater.inflate(R.layout.fragment_some, container, false)
 *     }
 *
 *     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *         super.onViewCreated(view, savedInstanceState)
 *         binding.button.setOnClickListener { ... }
 *     }
 * }
 * ```
 */
fun <Binding : ViewBinding> Fragment.viewBinding(
    binder: (View) -> Binding
): ReadOnlyProperty<Fragment, Binding> = object : ReadOnlyProperty<Fragment, Binding> {

    private var binding: Binding? = null

    init {
        viewLifecycleOwnerLiveData.observe(this@viewBinding) { viewLifecycleOwner ->
            viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_DESTROY) {
                    binding = null
                }
            })
        }
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): Binding {
        val binding = binding
        if (binding != null) return binding
        val lifecycleState = viewLifecycleOwner.lifecycle.currentState
        check(lifecycleState.isAtLeast(Lifecycle.State.INITIALIZED)) { "fragment view is destroyed" }
        return binder(thisRef.requireView()).also { this.binding = it }
    }
}

/**
 * Use for [RecyclerView.ViewHolder] or in custom [View]'s.
 *
 * Example [RecyclerView.ViewHolder]:
 *
 * ```
 * override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder = CustomViewHolder(
 *     parent.viewBinding { inflater -> ViewCustomBinding.inflate(inflater, parent, false) }
 * )
 * ```
 *
 * Example [View]:
 *
 * ```
 * class CustomView @JvmOverloads constructor(
 *     context: Context,
 *     attrs: AttributeSet? = null,
 *     defStyle: Int = 0
 * ) : View(context, attrs, defStyle) {
 *     private val binding = viewBinding { inflater -> ViewCustomBinding.inflate(inflater, this, false) }
 * }
 * ```
 */
inline fun <B : ViewBinding> View.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> B
): B = bindingInflater(LayoutInflater.from(context))