package com.sirius.test_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sirius.test_app.databinding.ActivityMainBinding
import com.sirius.test_app.databinding.ItemReviewBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dataModel = DataModel()

        setViews(dataModel)
        setRecyclerView(dataModel)
        setContentView(R.layout.activity_main)
    }

    private fun setViews(dataModel: DataModel) {
        binding.image.load(dataModel.image)
        binding.logo.load(dataModel.logo)
        binding.name.text = dataModel.name
        binding.grade.text = dataModel.gradeCnt
        binding.tag1.text = dataModel.tags[0]
        binding.tag2.text = dataModel.tags[1]
        binding.tag3.text = dataModel.tags[2]
        binding.reviewGrade.text = dataModel.gradeCnt
        binding.description.text = dataModel.description
        binding.btnInstall.text = dataModel.action.name
        binding.reviewRating.text = dataModel.rating.toString()
    }

    private fun setRecyclerView(dataModel: DataModel) {
        binding.reviews.layoutManager = LinearLayoutManager(this)
        binding.reviews.adapter = ReviewAdapter(dataModel.reviews)
    }
}

class ReviewAdapter(val reviews: List<ReviewModel>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewAdapter.ReviewHolder {
        val binding =
            ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewAdapter.ReviewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    override fun getItemCount() = reviews.size

    inner class ReviewHolder(var binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(review: ReviewModel) {
            binding.userImage.load(review.userImage)
            binding.userName.text = review.userName
            binding.date.text = review.date
            binding.userReview.text = review.message
        }
    }
}