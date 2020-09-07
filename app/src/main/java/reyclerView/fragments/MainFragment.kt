package reyclerView.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.skillboxkotlin.R
import kotlinx.android.synthetic.main.fragment_main.*
import reyclerView.extensions.StartButtonClick

class MainFragment: Fragment(R.layout.fragment_main) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

            if (savedInstanceState == null){
                btn_fragmentMain_linear.setOnClickListener {
                    createLinearListFragment()
                }

                btn_fragmentMain_grid.setOnClickListener {
                    createGridListFragment()
                }

                btn_fragmentMain_staggered.setOnClickListener {
                    createStaggeredListFragment()
                }
            }


    }

    private fun createLinearListFragment(){
        (activity as StartButtonClick).onLinearButtonClick()
    }

    private fun createGridListFragment(){
        (activity as StartButtonClick).onGridButtonClick()
    }

    private fun createStaggeredListFragment(){
        (activity as StartButtonClick).onStaggeredButtonClick()
    }
}