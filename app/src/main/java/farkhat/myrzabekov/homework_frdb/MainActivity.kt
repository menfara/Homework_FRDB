package farkhat.myrzabekov.homework_frdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import farkhat.myrzabekov.homework_frdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val animalDao = AnimalDao()

        binding.save.setOnClickListener {
            binding.progress.isVisible = true
            animalDao.saveData(getAnimal()) {
                binding.progress.isVisible = false
            }
        }

        binding.get.setOnClickListener {
            animalDao.getData()
        }

        binding.delete.setOnClickListener {
            animalDao.removeData()
        }

        animalDao.getDataLiveData.observe(this) {
            binding.user.text = it?.toString()
        }

        animalDao.updateLiveData.observe(this) {
            binding.userUpdate.text = it?.toString()
        }
    }

    private fun getAnimal() = Animal(
        name = "Leo",
        species = "Lion",
        age = 5,
        habitat = Habitat(name = "Savannah", climate = "Tropical"),
        favoriteFoods = listOf(
            Food(name = "Meat", type = FoodType.MEAT),
            Food(name = "Fish", type = FoodType.FISH),
            Food(name = "Vegetables", type = FoodType.VEGETABLES)
        )
    )
}

class AnimalDao : FRDBWrapper<Animal>() {
    override fun getTableName(): String = "Animal"

    override fun getClassType(): Class<Animal> = Animal::class.java
}

data class Animal(
    val name: String? = null,
    val species: String? = null,
    val age: Int? = null,
    val habitat: Habitat? = null,
    val favoriteFoods: List<Food>? = null
) {
    override fun toString(): String {
        return "name: $name, species: $species, age: $age, habitat: ${habitat.toString()}, favoriteFoods: $favoriteFoods"
    }
}

data class Habitat(
    val name: String? = null,
    val climate: String? = null
)

data class Food(
    val name: String? = null,
    val type: FoodType? = null
)

enum class FoodType {
    MEAT, FISH, VEGETABLES
}