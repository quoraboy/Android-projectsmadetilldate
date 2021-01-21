package com.example.aplidemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class Edit_Personal_Details extends Fragment {

    private Spinner dateDropdown , monthDropdown , yearDropdown, genderDropdown, maritialSpinnerDropdown;

    private Button updatePersonalInfo;



    private ImageView editProfileImage;

    public static final int PICK_IMAGE_REQUEST = 71;

    Uri uriprofileimage;

    private String profileImageUrl;

    private String name,date,gender,pincode,hometown,dob,aadhar,maritialStatus,email,address;
    private TextInputLayout nameEditText,genderEditText,emailEditText,hometownEditText,pincodeEditText,addressEditText;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Edit_Personal_Details() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_edit__personal__details, container, false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList <String> dateList = new ArrayList<String>();
        dateList.add("DD");
        dateList.add("01");dateList.add("02");dateList.add("03");dateList.add("04");dateList.add("05");dateList.add("06");dateList.add("07");
        dateList.add("08");dateList.add("09");dateList.add("10");dateList.add("11");dateList.add("12");dateList.add("13");dateList.add("14");
        dateList.add("15");dateList.add("16");dateList.add("17");dateList.add("18");dateList.add("19");dateList.add("20");dateList.add("21");
        dateList.add("22");dateList.add("23");dateList.add("24");dateList.add("25");dateList.add("26");dateList.add("27");dateList.add("28");
        dateList.add("29");dateList.add("30");dateList.add("31");

        final ArrayList<String> monthList = new ArrayList<String>();
        monthList.add("MM");
        monthList.add("01");monthList.add("02"); monthList.add("03");monthList.add("04");
        monthList.add("05"); monthList.add("06");monthList.add("07"); monthList.add("08");monthList.add("09");
        monthList.add("10");
        monthList.add("11");
        monthList.add("12");

        final ArrayList<String> yearList = new ArrayList<String>();
        yearList.add("YY");
        for(int i = 1970;i<2018;i++){
            String s = Integer.toString(i);
            yearList.add(s);
        }

        dateDropdown = (Spinner) v.findViewById(R.id.dateSpinnerDropdown);
        ArrayAdapter <String> adapterDate = new ArrayAdapter<>(getActivity() , android.R.layout.simple_spinner_dropdown_item, dateList);
        dateDropdown.setAdapter(adapterDate);

        monthDropdown = (Spinner) v.findViewById(R.id.monthSpinnerDropdown);
        ArrayAdapter<String> adapterMonth = new ArrayAdapter<>(getActivity() , android.R.layout.simple_spinner_dropdown_item, monthList);
        monthDropdown.setAdapter(adapterMonth);

        yearDropdown = (Spinner) v.findViewById(R.id.yearSpinnerDropdown);
        ArrayAdapter<String> adapterYear = new ArrayAdapter<>(getActivity() , android.R.layout.simple_spinner_dropdown_item, yearList);
        yearDropdown.setAdapter(adapterYear);

        final ArrayList<String> genderList = new ArrayList<String>();
        genderList.add("MALE");
        genderList.add("FEMALE");
        genderList.add("RATHER NOT SAY");

        genderDropdown = (Spinner) v.findViewById(R.id.genderSpinnerDropdown);
        ArrayAdapter<String> adapterGender = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item , genderList);
        genderDropdown.setAdapter(adapterGender);

        final ArrayList<String> maritialList = new ArrayList<>();
        maritialList.add("MARRIED");
        maritialList.add("UNMARRIED");
        maritialList.add("DIVORCED");
        maritialSpinnerDropdown = (Spinner) v.findViewById(R.id.maritialSpinnerDropdown);
        ArrayAdapter<String> adapterMaritial = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item , maritialList);
        maritialSpinnerDropdown.setAdapter(adapterMaritial);

        // EDITTEXT INFORAMTION

        nameEditText = (TextInputLayout) v.findViewById(R.id.name_edittext);
        emailEditText = (TextInputLayout) v.findViewById(R.id.email_edittext);
        hometownEditText = (TextInputLayout) v.findViewById(R.id.hometown_edittext);
        pincodeEditText = (TextInputLayout) v.findViewById(R.id.pincode_editText);
        addressEditText = (TextInputLayout) v.findViewById(R.id.permanentAddress_editText);
        editProfileImage = (ImageView) v.findViewById(R.id.imageprofile_image);

        // ADD PROFILE IMAGE BUTTON

        editProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        // UPDATE INFORMATION BUTTON

        updatePersonalInfo = (Button) v.findViewById(R.id.upadate_button);

        updatePersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameEdit = nameEditText.getEditText();
                EditText emailEdit = emailEditText.getEditText();
                EditText hometownEdit = hometownEditText.getEditText();
                EditText pincodeEdit = pincodeEditText.getEditText();
                EditText addressEdit = addressEditText.getEditText();

                name = nameEdit.getText().toString();
                email = emailEdit.getText().toString();
                hometown  = hometownEdit.getText().toString();
                pincode = pincodeEdit.getText().toString();
                address = addressEdit.getText().toString();
                maritialStatus = maritialSpinnerDropdown.getSelectedItem().toString();
                gender = genderDropdown.getSelectedItem().toString();
                String day = dateDropdown.getSelectedItem().toString();
                String month = monthDropdown.getSelectedItem().toString();
                String year = yearDropdown.getSelectedItem().toString();
                date = day + month + year;

                if(name.isEmpty() || aadhar.isEmpty() || email.isEmpty() || hometown.isEmpty() || pincode.isEmpty() || address.isEmpty()
                        || maritialStatus.isEmpty() || gender.isEmpty() || day.equalsIgnoreCase("DD") || month.equalsIgnoreCase("MM")
                        || year.equalsIgnoreCase("YY")) {

                    Toast.makeText(getActivity(), "PLEASE FILL ALL THE CEREDENTIALS", Toast.LENGTH_SHORT).show();
                }
                else if(uriprofileimage == null){
                    Toast.makeText(getActivity(),"PLEASE ADD A PROFILE IMAGE",Toast.LENGTH_SHORT).show();
                }

                else {

//                    uploadImage();
                }
            }
        });

        return  v;
    }




    private void chooseImage(){

        Intent intent = new Intent();
        intent.setType("images/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!= null){
            uriprofileimage=data.getData();
            editProfileImage.setImageURI(uriprofileimage);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver() , uriprofileimage);
                editProfileImage.setImageBitmap(bitmap);

            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
