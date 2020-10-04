package module17

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skillboxkotlin.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private var personAdapter: UserAdapter? = null
    private val personListViewModel: PersonListViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        fab_recycleViewListFragment_addPerson.setOnClickListener {
            addPerson()
        }
        observeViewModelState()
    }

    private fun observeViewModelState(){
        personListViewModel.personLiveData.observe(viewLifecycleOwner){ newList: List<Person> ->
            personAdapter?.users = newList
            personAdapter?.notifyDataSetChanged()
        }

        personListViewModel.showToastLiveData
            .observe(viewLifecycleOwner){Toast.makeText(requireContext(), "Element was deleted!", Toast.LENGTH_SHORT).show()}
    }

    private fun initList() {
        personAdapter = UserAdapter { position: Int ->
            deletePerson(position)
//            findNavController().navigate(R.id.action_mainFragment_to_detailInfoFragment)
        }

        with(rv_recycleViewListFragment_personList) {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun deletePerson(position: Int) {
        personListViewModel.deletePerson(position)
    }

    private fun addPerson(){
        personListViewModel.addPerson()
        rv_recycleViewListFragment_personList.scrollToPosition(0)
    }


}