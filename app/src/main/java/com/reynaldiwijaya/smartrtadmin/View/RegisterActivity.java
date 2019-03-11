package com.reynaldiwijaya.smartrtadmin.View;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.reynaldiwijaya.smartrtadmin.Presenter.Register.RegisterContract;
import com.reynaldiwijaya.smartrtadmin.Presenter.Register.RegisterPresenter;
import com.reynaldiwijaya.smartrtadmin.R;
import com.reynaldiwijaya.smartrtadmin.Utills.Constants;

import java.io.IOException;
import java.sql.Array;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    @BindView(R.id.btn_selected_photo)
    Button btnSelectedPhoto;
    @BindView(R.id.img_user)
    CircleImageView imgUser;
    @BindView(R.id.edt_namaLengkap)
    TextInputEditText edtNamaLengkap;
    @BindView(R.id.no_KTP)
    TextInputEditText noKTP;
    @BindView(R.id.alamat)
    TextInputEditText alamat;
    @BindView(R.id.status)
    TextInputEditText status;
    @BindView(R.id.rb_laki2)
    RadioButton rbLaki2;
    @BindView(R.id.rb_perempuan)
    RadioButton rbPerempuan;
    @BindView(R.id.profesi)
    TextInputEditText profesi;
    @BindView(R.id.edt_noTelp)
    TextInputEditText edtNoTelp;
    @BindView(R.id.edt_email)
    TextInputEditText edtEmail;
    @BindView(R.id.edt_username)
    TextInputEditText edtUsername;
    @BindView(R.id.edt_password)
    TextInputEditText edtPassword;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.ib_date)
    ImageButton ibDate;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.rg_jenisKelamin)
    RadioGroup rgJenisKelamin;
    @BindView(R.id.spinner)
    Spinner spinner;

    private ProgressDialog progressDialog;
    private RegisterPresenter registerPresenter = new RegisterPresenter(this, this);
    private String jenkel;
    private String level;
    private String nama_lengkap, edtAlamat, edtStatus, tglLahir, edtProfesi, email, username, password, path;
    private Uri filepath;
    private String no_ktp, no_tlp, konfirmasi;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d("TAG", year + "-" + month + "-" + dayOfMonth);
                String date = year + "-" + month + "-" + dayOfMonth;
                tvDate.setText(date);
            }
        };

        setRequestPermission();

        String[] item = getResources().getStringArray(R.array.item);
        spinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, item));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                level = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.btn_selected_photo, R.id.rb_laki2, R.id.rb_perempuan,R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_selected_photo:
                showFileChooser(Constants.REQ_CHOOSE_FILE);
                break;
            case R.id.rb_laki2:
                jenkel = rbLaki2.getText().toString();
                break;
            case R.id.rb_perempuan:
                jenkel = rbPerempuan.getText().toString();
                break;
            case R.id.btn_send:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure want to Register ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getData();
                        checkData();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                break;
        }
    }

    private void setRequestPermission() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.STORAGE_REQUEST_PERMISSION);

    }

    private void checkData() {

        boolean isValid = true;

        if (TextUtils.isEmpty(nama_lengkap)) {
            edtNamaLengkap.setError(getString(R.string.error_message));
            edtNamaLengkap.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(edtAlamat)) {
            alamat.setError(getString(R.string.error_message));
            alamat.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(edtStatus)) {
            status.setError(getString(R.string.error_message));
            status.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(tglLahir)) {
            Toasty.error(this, "Tanggal Lahir tidak boleh kosong", Toasty.LENGTH_SHORT).show();
            isValid = false;
        }

        if (TextUtils.isEmpty(edtProfesi)) {
            profesi.setError(getString(R.string.error_message));
            profesi.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(email)) {
            edtEmail.setError(getString(R.string.error_message));
            edtEmail.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(username)) {
            edtUsername.setError(getString(R.string.error_message));
            edtUsername.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            edtPassword.setError(getString(R.string.error_message));
            edtPassword.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(no_ktp)) {
            noKTP.setError(getString(R.string.error_message));
            noKTP.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(no_tlp)) {
            edtNoTelp.setError(getString(R.string.error_message));
            edtNoTelp.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(level)) {
            Toasty.error(this, "Jabatan tidak boleh kosong", Toasty.LENGTH_SHORT).show();
            isValid = false;
        }


        if (TextUtils.isEmpty(jenkel)) {
            Toasty.error(this, "Jenis Kelamin tidak boleh kosong", Toasty.LENGTH_SHORT).show();
            isValid = false;
        }

        if (isValid) {
            sendData();
        }
    }

    private void sendData() {
        registerPresenter.doRegister(nama_lengkap, no_ktp, edtAlamat, edtStatus, tglLahir, jenkel, edtProfesi,
                no_tlp, email, username, password, path = getPath(filepath), konfirmasi, konfirmasi);
    }

    private void getData() {
        nama_lengkap = edtNamaLengkap.getText().toString();
        edtAlamat = alamat.getText().toString();
        edtStatus = status.getText().toString();
        tglLahir = tvDate.getText().toString();
        edtProfesi = profesi.getText().toString();
        email = edtEmail.getText().toString();
        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();
        no_tlp = edtNoTelp.getText().toString();
        no_ktp = noKTP.getText().toString();

        if (level.equals("Ketua RT")) {
            konfirmasi = "2";
        } else if (level.equals("Sekretaris")) {
            konfirmasi = "2";
        }else if (level.equals("Bendahara")) {
            konfirmasi = "2";
        } else if (level.equals("Warga")) {
            konfirmasi = "1";
        }

        Log.i("Konfirmasi", konfirmasi);
        Log.i("Jabatan", level);

    }

    private void showFileChooser(int reqChooseFile) {
        Intent toGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(toGallery, reqChooseFile);
        Log.i("Gallery", "Masuk Galley");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQ_CHOOSE_FILE) {
            if (resultCode == RESULT_OK) {
                if (data.getData() != null) {
                    filepath = data.getData();
                }
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                    imgUser.setImageBitmap(bitmap);
                    btnSelectedPhoto.setAlpha(0f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String getPath(Uri filepath) {
        Cursor cursor = getContentResolver().query(filepath, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images
                        .Media._ID + " = ? ", new String[]{document_id}, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    @Override
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Try to Register");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showSuccesMessage(String msg) {
        Toasty.success(this, msg, Toasty.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showFailureMessage(String msg) {
        Toasty.error(this, msg, Toasty.LENGTH_SHORT).show();
    }

    @OnClick(R.id.ib_date)
    public void onViewClicked() {
        getDate();
    }

    private void getDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
