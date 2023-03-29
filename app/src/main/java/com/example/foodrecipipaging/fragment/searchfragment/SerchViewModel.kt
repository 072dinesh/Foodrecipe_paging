package com.example.foodrecipipaging.fragment.searchfragment

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings.Global.getString
import android.provider.Settings.System.getString
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController

import com.example.foodrecipipaging.R
import com.example.foodrecipipaging.di.FoodAPI
import com.example.foodrecipipaging.model.Foodresipi
import com.example.foodrecipipaging.model.NutrientX
import com.example.foodrecipipaging.model.resipi
import com.example.foodrecipipaging.repository.FoodRepository
import com.example.foodrecipipaging.util.BaseViewModel
import com.example.foodrecipipaging.util.DiffUtilExt
import com.example.foodrecipipaging.util.NetworkResult
import com.example.foodrecipipaging.util.PrefManager.getString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.properties.Delegates


@HiltViewModel
class SerchViewModel @Inject constructor(private val repository: FoodRepository, application: Application) : BaseViewModel(application) {

    val myResponseView: MutableLiveData<NetworkResult<resipi>> = MutableLiveData()

    private val _allUsers = MutableLiveData<NetworkResult<Foodresipi>>()
    val allUsers: LiveData<NetworkResult<Foodresipi>>
        get() = _allUsers



    var mContext = application


    private var vid = -1

    @RequiresApi(Build.VERSION_CODES.M)
    fun getPost2(id: Int) {

        if (vid == id) {
            return
        } else {
            vid = id
        }
        viewModelScope.launch {
            _allUsers.value = NetworkResult.Loading()

            if (isConnected()) {


                val response: Response<Foodresipi> = repository.getPost2(id)
                _allUsers.value = handleResponse(response)


            } else {
                _allUsers.value = NetworkResult.Error(
                    mContext.getString(R.string.no_internet)
                )
            }


        }


    }


    fun CarbanSugarpre(nutration: List<NutrientX>): String {

        lateinit var tvd: String
        var def = DecimalFormat("#.##")

        nutration?.forEach {
            var pers = it.amount
            if (it.name == "Fat") {


                tvd = this.mContext.getString(R.string.pers_text, def.format(pers))

            }

        }

        return tvd

    }


    fun CarbanSugarprogrss(nutration: List<NutrientX>): Int {


        var tvd by Delegates.notNull<Int>()

        nutration?.forEach {

            if (it.name == "Sugar") {

                tvd = it.percentOfDailyNeeds!!.toInt()
            }
        }

        return tvd

    }

    fun carbanSugar(nutration: List<NutrientX>): String {

        lateinit var tvd: String

        nutration?.forEach {

            if (it.name == "Sugar") {

                tvd = it.amount.toString()

            }

        }
        return tvd
    }

    fun CarbanSugarFat(nutration: List<NutrientX>): String {

        lateinit var tvd: String

        nutration?.forEach {

            if (it.name == "Sugar") {

                tvd = it.unit.toString()
                //tvd = this.carbanSugar() .getString(R.string.pers_text, def.format(pers))

            }

        }

        return tvd

    }

    //secod
    fun text_view_progress2(nutration: List<NutrientX>): String {

        lateinit var tvd: String

        nutration?.forEach {

            if (it.name == "Fat") {

                tvd = it.amount.toString()

            }
        }
        return tvd
    }

    fun tv_fat_unit(nutration: List<NutrientX>): String {

        lateinit var tvd: String

        nutration?.forEach {

            if (it.name == "Fat") {

                tvd = it.unit.toString()
                //tvd = this.carbanSugar() .getString(R.string.pers_text, def.format(pers))

            }
        }
        return tvd
    }

    fun tv_fat_per(nutration: List<NutrientX>): String {

        lateinit var tvd: String
        var def = DecimalFormat("#.##")

        nutration?.forEach {
            var pers = it.amount
            if (it.name == "Fat") {

                tvd = this.mContext.getString(R.string.pers_text, def.format(pers))
            }

        }

        return tvd
    }

    fun progressBar_fat(nutration: List<NutrientX>): Int {


        var tvd by Delegates.notNull<Int>()

        nutration?.forEach {

            if (it.name == "Fat") {

                tvd = it.percentOfDailyNeeds!!.toInt()

            }
        }
        return tvd

    }


//thre

    fun text_view_progress3(nutration:List<NutrientX>):String {

        lateinit var tvd: String

        nutration?.forEach {

            if (it.name == "Protein") {

                tvd = it.amount.toString()

            }

        }
        return tvd
    }


    fun tv_pro_unit(nutration:List<NutrientX>):String{

        lateinit var tvd:String

        nutration?.forEach {

            if (it.name == "Protein") {

                tvd = it.unit.toString()

            }

        }

        return tvd

    }

    fun tv_pro_per(nutration:List<NutrientX>):String{

        lateinit var tvd:String
        var def = DecimalFormat("#.##")

        nutration?.forEach {
            var pers = it.amount
            if (it.name == "Protein") {

                tvd = this.mContext.getString(R.string.pers_text, def.format(pers))

            }
        }

        return tvd

    }

    fun progressBar_protien(nutration:List<NutrientX>):Int{

         var tvd by Delegates.notNull<Int>()

        nutration?.forEach {

            if (it.name == "Protein") {

                tvd = it.percentOfDailyNeeds!!.toInt()

            }
        }
        return tvd
    }

//four

    fun text_view_progress4(nutration:List<NutrientX>):String {

        lateinit var tvd: String

        nutration?.forEach {

            if (it.name == "Calories") {

                        tvd = it.amount.toString()
            }

        }
        return tvd
    }

    fun tv_cal_unit(nutration:List<NutrientX>):String{

        lateinit var tvd:String

        nutration?.forEach {

            if (it.name == "Calories") {

                tvd = it.unit.toString()

            }

        }

        return tvd

    }

    fun progressBar_cal(nutration:List<NutrientX>):Int{

        var tvd by Delegates.notNull<Int>()

        nutration?.forEach {
            if (it.name == "Calories") {

                tvd = it.percentOfDailyNeeds!!.toInt()
            }
        }

        return tvd

    }


    fun seedataurl(food:Foodresipi){
        val intent = Intent(Intent.ACTION_VIEW, )
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.data = Uri.parse(food.sourceUrl)
       mContext.startActivity(intent)

    }

    fun healthScoreRe(food:Foodresipi):Float{
        var tvd by Delegates.notNull<Float>()
        val rating: Float = food?.healthScore!!.toFloat() * 5 / 100
            tvd = rating
        return tvd

    }







}