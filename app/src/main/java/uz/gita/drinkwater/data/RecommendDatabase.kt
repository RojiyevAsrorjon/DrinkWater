package uz.gita.drinkwater.data

class RecommendDatabase {
    val listOfRecommends = ArrayList<String>()

    init {
        loadData()
    }

    private fun loadData() {
        listOfRecommends.add("Drinking water keeps your body temperature within a normal range")
        listOfRecommends.add("Drinking water helps you eliminate waste through urine, sweat, and bowel movements")
        listOfRecommends.add("Drinking water keep your skin looking healthy")
        listOfRecommends.add("You can treat mild dehydration by drinking more water and other fluids")
        listOfRecommends.add("Caution! Drinking too much water can dilute the electrolytes in your blood")
        listOfRecommends.add("Caution! Don`t drink water while standing")
    }
}