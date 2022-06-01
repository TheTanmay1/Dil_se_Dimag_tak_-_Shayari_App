package com.unwiringapps.dilsedimagtak_shayariapp.adapter

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.unwiringapps.dilsedimagtak_shayariapp.AllShayariActivity
import com.unwiringapps.dilsedimagtak_shayariapp.Model.ShayariModel
import com.unwiringapps.dilsedimagtak_shayariapp.R
import com.unwiringapps.dilsedimagtak_shayariapp.databinding.ItemShayariBinding


class AllShayariAdapter(
    val allShayariActivity: AllShayariActivity,
    val shayarilistrr: ArrayList<ShayariModel>
) : RecyclerView.Adapter<AllShayariAdapter.ShayariViewHolder>() {


    class ShayariViewHolder(val binding: ItemShayariBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShayariViewHolder {
        return ShayariViewHolder(
            ItemShayariBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShayariViewHolder, position: Int) {
        holder.binding.itemShayari.text = shayarilistrr[position].data.toString()


        if (position % 5 == 0) {
            holder.binding.ituu1.setBackgroundResource(R.drawable.gradient_1)
        } else if (position % 5 == 1) {
            holder.binding.ituu1.setBackgroundResource(R.drawable.gradient_2)
        } else if (position % 5 == 2) {
            holder.binding.ituu1.setBackgroundResource(R.drawable.gradient_3)
        } else if (position % 5 == 3) {
            holder.binding.ituu1.setBackgroundResource(R.drawable.gradient_4)

        } else if (position % 5 == 4) {
            holder.binding.ituu1.setBackgroundResource(R.drawable.gradient_5)
        } else if (position % 5 == 5) {
            holder.binding.ituu1.setBackgroundResource(R.drawable.gradient_6)
        }



        holder.binding.sharu.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n${shayarilistrr[position].data.toString()}  \n\n"

                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                allShayariActivity.startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
        }

        holder.binding.btnCopa.setOnClickListener {
            val clipboard: ClipboardManager? =
                allShayariActivity.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText("label", shayarilistrr[position].data.toString())
            clipboard?.setPrimaryClip(clip)
            Toast.makeText(allShayariActivity, R.string.copied_to_clipboard, Toast.LENGTH_SHORT).show()
        }


        holder.binding.whaaaatsaaaap.setOnClickListener {
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, shayarilistrr[position].data.toString())
            try {
                allShayariActivity.startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {
            }
        }

    }

    override fun getItemCount() = shayarilistrr.size


}
