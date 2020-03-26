package com.tailoredapps.androidapptemplate.base.ui

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Use in [FragmentActivity].
 *
 * Example:
 *
 * ```
 * class MainActivity : AppCompatActivity() {
 *
 *     private val binding by viewBinding(ActivityMainBinding::inflate) // inflation happens via extension
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         setContentView(binding.root) // need to set root of binding
 *         binding.button.setOnClickListener { ... }
 *     }
 * }
 * ```
 */
inline fun <B : ViewBinding> FragmentActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> B
): Lazy<B> = lazy { bindingInflater(layoutInflater) }

/**
 * Use in [Fragment].
 *
 * Example:
 *
 * ```
 * class SomeFragment : Fragment(R.layout.fragment_some) { // layout inflation happens in Fragment
 *
 *     private val binding by viewBinding(FragmentSomeBinding::bind) // binding via extension
 *
 *     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *         super.onViewCreated(view, savedInstanceState)
 *         binding.button.setOnClickListener { ... }
 *     }
 * }
 * ```
 */
fun <B : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> B
): ReadOnlyProperty<Fragment, B> = object : ReadOnlyProperty<Fragment, B> {

    private var binding: B? = null

    init {
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                viewLifecycleOwnerLiveData.observe(this@viewBinding) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            binding = null
                        }
                    })
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): B {
        val binding = binding
        if (binding != null) return binding
        val lifecycleState = viewLifecycleOwner.lifecycle.currentState
        check(lifecycleState.isAtLeast(Lifecycle.State.INITIALIZED)) { "fragment view is destroyed" }
        return viewBindingFactory(thisRef.requireView()).also { this.binding = it }
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