package module17

import kotlin.random.Random

class PersonRepository {
    fun generatePersons(count: Int): List<Person>{
        val names = listOf("Andrey", "Vladimir", "Tomas", "John", "Sveta", "Bob", "Lora", "Mary", "Donna")
        val avatarsLinks = listOf(
            "https://cdn.pixabay.com/photo/2016/11/18/23/38/child-1837375__340.png",
            "https://cdn.pixabay.com/photo/2016/04/26/07/57/woman-1353825_960_720.png",
            "https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png",
            "https://cdn.pixabay.com/photo/2014/04/03/10/32/user-310807_960_720.png",
            "https://cdn.pixabay.com/photo/2016/04/26/07/20/woman-1353803_960_720.png",
            "https://cdn.pixabay.com/photo/2016/08/20/05/36/avatar-1606914_960_720.png",
            "https://cdn.pixabay.com/photo/2016/04/01/11/25/avatar-1300331_960_720.png",
            "https://cdn.pixabay.com/photo/2018/08/28/13/29/avatar-3637561_960_720.png",
            "https://cdn.pixabay.com/photo/2016/03/31/20/27/avatar-1295773_960_720.png")
        val persons = mutableListOf<Person>()
        for (counter in 0..count){
            persons.add(
                Person(
                    id = Random.nextLong(),
                    name = names.random(),
                    avatarLink = avatarsLinks.random(),
                    age = Random.nextInt(18, 70),
                    isDeveloper = Random.nextBoolean()
                )
            )
        }
        return persons
    }

    fun createPerson(): Person {
        return generatePersons(1).first()
    }

    fun deletePerson(personList: List<Person>, position: Int): List<Person>{
        return personList.filterIndexed { index, person -> index != position }
    }
}