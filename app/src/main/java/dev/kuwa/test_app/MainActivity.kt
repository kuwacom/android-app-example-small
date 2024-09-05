package dev.kuwa.test_app

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ナビゲーションバーを差し引いたウィンドウ処理にする？っぽい
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the fragment
        val listFragment = ListFragment()

        // FragmentManagerを使うことでFragmentの追加や削除などの制御を行うことができる
        val transaction = supportFragmentManager.beginTransaction()

        // addメソッドで"どこ"に"どの"Fragmentを追加するのかを決める
        transaction.add(R.id.fragmentContainer, listFragment)

        if (savedInstanceState == null) {
            //commitメソッドで追加したメソッドを反映する
            transaction.commit()
        }

        // ButtonのEvent
        val buttonAddItem: Button = findViewById(R.id.buttonAddItem)
        buttonAddItem.setOnClickListener {
            listFragment.addItem("Item ${System.currentTimeMillis()}")
        }
    }
}