package com.apps.finalproject.ui.detail

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import com.apps.finalproject.R
import com.apps.finalproject.databinding.FragmentDetailBinding
import com.apps.finalproject.remote.model.Cart
import com.apps.finalproject.remote.model.Review
import com.apps.finalproject.remote.model.Trending
import com.apps.finalproject.ui.ViewModelFactory
import com.apps.finalproject.ui.adapter.ListReviewAdapter
import com.apps.finalproject.ui.cart.CartActivity
import com.apps.finalproject.ui.cart.CartViewModel
import com.apps.finalproject.ui.favorite.FavoriteActivity
import com.apps.finalproject.ui.view.AuthActivity
import com.apps.finalproject.utils.AppPref
import com.apps.finalproject.utils.Utils.peringatan
import com.apps.finalproject.utils.stringToObject
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {
    var quantity = 1
    var sub_Total = 0
    var varian_id = ""
    private lateinit var binding: FragmentDetailBinding

    private val detailViewModel: DetailViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    private val cartViewMOdel: CartViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    private var favorite = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val dataTrending = arguments?.getString(EXTRA_PRODUCT)
        val data = stringToObject(dataTrending, Trending::class.java)
        varian_id = data?.variant?.get(0)?.id_product.toString()
        sub_Total = data?.variant?.get(0)?.price ?: 0
        populateDataProduct(data)

        if (data != null) {
            data.variant?.get(0)?.let { detailViewModel.getMyFavorite(it.name) }
        }

        binding.ivFavorite.setOnClickListener{
            if (favorite)
                data?.let { productName -> detailViewModel.deleteFavorite(productName) }
            else
                data?.let { productName -> detailViewModel.addFavorite(productName) }
        }

        Log.d("getreview", "$dataTrending")
        detailViewModel.getReview()

        detailViewModel.review.observe(viewLifecycleOwner){
            populateData(it)

        }

        detailViewModel.myFavorite.observe(viewLifecycleOwner){
            favorite = it
            populateDataFavorite(it)
        }

        binding.apply {
            tvSubtotl.text = "Rp $sub_Total"
            btnPlusProduk.setOnClickListener {
                if (quantity >= 0) {
                    quantity += 1
                    tvQyt.text = "$quantity"

                    val hasil = sub_Total * quantity
                    tvSubtotl.text = "Rp$hasil"
                }
            }
            btnMinProduk.setOnClickListener {
                if (quantity > 0) {
                    quantity -=1
                    tvQyt.text = "$quantity"

                    val hasil = sub_Total * quantity
                    tvSubtotl.text = "Rp$hasil"
                }
            }

            tvAddtobg.setOnClickListener {
                if (AppPref.token == "") {
                    peringatan(requireContext(), getString(R.string.haraplogin))
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                }
                else if ((varian_id != "") and (quantity != 0)){
                    val cart = Cart(quantity,varian_id)
                    Log.d("cart",AppPref.token)
                    cartViewMOdel.AddCart(cart)
                    cartViewMOdel.getResponse().observe(requireActivity()){
                        if (it == "200"){
                            getCustomLayoutDialog(R.layout.dialog_cart_sukses,"sukses",R.color.colorPrimary)
                        }else{
                            getCustomLayoutDialog(R.layout.dialog_cart_sukses,"GAGAL",R.color.colorPrimary)
                        }
                    }
                }
            }
        }

        detailViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

    }

    private fun getCustomLayoutDialog(layoutId: Int,status:String, colorId: Int) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutId)

        val lp = WindowManager.LayoutParams()
        if (dialog.window != null) {

            lp.copyFrom(dialog.window?.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            val positiveButton = dialog.findViewById<Button>(R.id.btn_keranjang_saya)
//            val text = dialog.findViewById<TextView>(R.id.tx_info)
//            text.text = "Gagal memasukkan ke keranjang"

            positiveButton.setBackgroundColor(ContextCompat.getColor(requireContext(), colorId))
            positiveButton.setOnClickListener {
                requireContext().startActivity(Intent(requireContext(),CartActivity::class.java))
                dialog.cancel()
            }

            dialog.show()

            dialog.window?.attributes = lp
        }
    }

    private fun populateDataFavorite(isFavorite: Boolean) {
        binding.ivFavorite.setImageResource(
            if (isFavorite)
                R.drawable.ic_favorite_click
            else
                R.drawable.ic_favorite
        )
    }

    private fun populateDataProduct(dataTrending: Trending?) {
        if (dataTrending != null) {
            with(binding) {
                Glide.with(this@DetailFragment)
                    .load(dataTrending.images?.get(0))
                    .into(imDetailProduk)
                tvDetailProduk.text = dataTrending.variant?.get(0)?.name ?: "-"
                tvHargaProduk.text = dataTrending.variant?.get(0)?.price.toString()
                ratingBarProduk.rating = dataTrending.average?.toFloat() ?: 0f
                tvRate.text = dataTrending.average.toString()
                tvQuantity.text = dataTrending.variant?.get(0)?.quantity.toString() + " ml"
                Glide.with(this@DetailFragment)
                    .load(dataTrending.brand?.logo)
                    .into(imBrand)
                brandName.text = dataTrending.brand?.name ?: ""
                tabValue.text = dataTrending.brand?.description
            }
        } else {
            peringatan(requireContext(), "Koneksi tidak stabil, Terjadi kesalahan")
            requireActivity().onBackPressed()
        }
    }

    private fun populateData(listReview: List<Review>) {
        val listReviewAdapter = ListReviewAdapter(listReview)
        binding.rvReview.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = listReviewAdapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_PRODUCT = "product"
    }
}



