package cubex.mahesh.telephony_oct10pm

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var uri:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendSMS.setOnClickListener {
            var sManager = SmsManager.getDefault()
            var sintent = Intent(this@MainActivity,
                    SendActivity::class.java)
            var dintent = Intent(this@MainActivity,
                    DeliverActivity::class.java)
            var spintent = PendingIntent.getActivity(this@MainActivity,
                    0,sintent,0)
            var dpintent = PendingIntent.getActivity(this@MainActivity,
                    0,dintent,0)
            sManager.sendTextMessage(et1.text.toString(),null,
                   et2.text.toString(),spintent,dpintent )

        }
        call.setOnClickListener {

            var i = Intent( )
            i.setAction(Intent.ACTION_CALL)
            i.setData(Uri.parse("tel:"+et1.text.toString()))
            startActivity(i)
        }

        attach.setOnClickListener {
            var i = Intent( )
            i.setAction(Intent.ACTION_GET_CONTENT)
            i.setType("*/*")
            startActivityForResult(i,123)
        }
        sendMail.setOnClickListener {
                    var i = Intent( )
                    i.setAction(Intent.ACTION_SEND)
                    i.putExtra(Intent.EXTRA_EMAIL,
                                                arrayOf(et3.text.toString()))
                    i.putExtra(Intent.EXTRA_SUBJECT, et4.text.toString())
                    i.putExtra(Intent.EXTRA_TEXT,et5.text.toString())
                    i.putExtra(Intent.EXTRA_STREAM,uri)
                    i.setType("message/rfc822") // enable MIME type of data
                    startActivity(i)
        }
    } // onCreate( )

    override fun onActivityResult(requestCode: Int,
                                                   resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        uri = data!!.getData( )
    }


} // MainActivity
