package com.example.facebookapp

import android.Manifest
import android.R
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.facebookapp.databinding.FragmentPublishBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class PublishFragment(private val userActual:User) : Fragment() {
    private  var _binding:FragmentPublishBinding?= null
    private val binding get() = _binding!!
    private var permissionAccepted = false
    var listener: OnNewPostListenner?= null
    var file: File? =null
    var image:String=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPublishBinding.inflate(inflater, container, false)
        val view = binding.root
        val cameralauncher = registerForActivityResult(StartActivityForResult(), :: cameraonResult)
        val galerylauncher = registerForActivityResult(StartActivityForResult(), :: galeryonResult)

        binding.cameraBtn.setOnClickListener {
            requestPermissions(arrayOf(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE),1)
            if(permissionAccepted) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                file = File("${activity?.getExternalFilesDir(null)}/photo.png")
                val uri =
                    FileProvider.getUriForFile(requireContext(), context?.packageName!!, file!!)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                image=uri.toString()
                Log.e(">>camara>",image)
                cameralauncher.launch(intent)
            }
        }
        binding.galeryBtn.setOnClickListener {
            requestPermissions(arrayOf(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE),1)
            if(permissionAccepted) {

                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                galerylauncher.launch(intent)
            }

        }
        binding.publicarBtn.setOnClickListener {

            listener?.let {
                val user = this.userActual.name.toString()
                val city = binding.spinner.selectedItem.toString()
                val description = binding.descPostText.text.toString()
                Log.e("description",description)
                val date = getCurrentDateTime().toString("yyyy/MM/dd HH:mm:ss")
                if(description.isEmpty() or city.isEmpty() or date.isEmpty() or image.isEmpty()){
                    Toast.makeText(activity,"Llene todos los datos para publicar el post",Toast.LENGTH_LONG).show()
                }else{
                    it.onNewPost(user,date,description,city,image)
                    binding.descPostText.clearComposingText()
                    Toast.makeText(activity,"Publicado",Toast.LENGTH_LONG).show()
                }
            }
        }
        // Inflate the layout for this fragment
        return view
    }
    fun cameraonResult(result: ActivityResult){
        if(result.resultCode == RESULT_OK){
            val bitmap = BitmapFactory.decodeFile(file?.path)
            val thumbnail = Bitmap.createScaledBitmap(bitmap, bitmap.width/4,bitmap.height/4,true)
            binding.imageNewPost.setImageBitmap(thumbnail)
        }else if(result.resultCode == RESULT_CANCELED){
            file = null
        }
    }
    fun galeryonResult(result: ActivityResult){
        if(result.resultCode == RESULT_OK){
            val sourceTreeUri: Uri = result.data?.data!!
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                requireContext().contentResolver.takePersistableUriPermission(
                    sourceTreeUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }
            val uriImage = result.data?.data
            uriImage?.let {
                binding.imageNewPost.setImageURI(uriImage)
                image=uriImage.toString()
                Log.e(">>galeria>",image)
            }
        }else if(result.resultCode == RESULT_CANCELED){
            file = null
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==1){
            var allGrand = true
            for(result in grantResults){
                if(result == PackageManager.PERMISSION_DENIED){
                    allGrand = false
                    break
                }
            }

            permissionAccepted = allGrand
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this) }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time }


    interface OnNewPostListenner{
        fun onNewPost(name:String,date:String,description:String,city:String,imagePost:String)
    }
    companion object {

        @JvmStatic
        fun newInstance(user:User) = PublishFragment(user).apply {
        }
    }

}