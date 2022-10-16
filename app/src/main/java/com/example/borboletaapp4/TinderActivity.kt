package com.example.borboletaapp4

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.yuyakaido.android.cardstackview.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.example.borboletaapp4.CardDiffCallback
import com.example.borboletaapp4.CardStackAdapter
import com.example.borboletaapp4.R
import com.example.borboletaapp4.models.card
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.StackFrom
import java.util.ArrayList

class TinderActivity : AppCompatActivity(),CardStackListener {
    private val drawerLayout by lazy { findViewById<DrawerLayout>(R.id.drawer_layout) }
    private val cardStackView by lazy { findViewById<CardStackView>(R.id.card_stack) }
    private val manager by lazy { CardStackLayoutManager(this,this)}
    private val adapter by lazy { CardStackAdapter(createCards()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tinder)
        setupCardStackView()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        if (direction ==Direction.Left){
            println("cardleft")
            ItsRewindTime()
        } else
            if (direction ==Direction.Right){

            } else
                if (direction == Direction.Bottom){

                } else
                    if (direction == Direction.Top){

                    }
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        if (manager.topPosition == adapter.itemCount - 5) {
            paginate()
        }
    }

    private fun ItsRewindTime(){
        reload()
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.name)
        Log.d("CardStackView", "onCardAppeared: ($position) ${textView.text}")
    }

    override fun onCardDisappeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.name)
        Log.d("CardStackView", "onCardDisappeared: ($position) ${textView.text}")
    }

    private fun setupCardStackView() {
        initialize()
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.BottomAndRight)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.7f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.FREEDOM)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.Manual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }
    private fun paginate() {
        val old = adapter.getCards()
        val new = old.plus(createCards())
        val callback = CardDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setCards(new)
        result.dispatchUpdatesTo(adapter)
    }
    private fun reload() {
        val old = adapter.getCards()
        val new = createCards()
        val callback = CardDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(adapter)
    }

    private fun createCard(): card {
        return card(
            name = "Professional 1", role = "Asesor", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = ResourcesCompat.getDrawable(resources, R.drawable.ejemplo1, null)!!
        )
    }

    private fun createCards(): List<card> {
        val cards = ArrayList<card>()
        cards.add( card(
            name = "Professional 1", role = "Asesor", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = ResourcesCompat.getDrawable(resources, R.drawable.ejemplo1, null)!!
        ))
        cards.add( card(
            name = "Professional 2", role = "Asesor", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = ResourcesCompat.getDrawable(resources, R.drawable.ejemplo1, null)!!
        ))
        cards.add( card(
            name = "Professional 3", role = "Asesor", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = ResourcesCompat.getDrawable(resources, R.drawable.ejemplo1, null)!!
        ))
        cards.add( card(
            name = "Professional 4", role = "Asesor", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = ResourcesCompat.getDrawable(resources, R.drawable.ejemplo1, null)!!
        ))
        cards.add( card(
            name = "Professional 5", role = "Asesor", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = ResourcesCompat.getDrawable(resources, R.drawable.ejemplo1, null)!!
        ))
        cards.add( card(
            name = "Professional 6", role = "Asesor", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = ResourcesCompat.getDrawable(resources, R.drawable.ejemplo1, null)!!
        ))
        cards.add( card(
            name = "Professional 7", role = "Asesor", description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = ResourcesCompat.getDrawable(resources, R.drawable.ejemplo1, null)!!
        ))
        return cards
    }

    fun readData(){
        val data = FirebaseFirestore.getInstance()

    }

}