package com.example.healthlink.profileSetting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthlink.AppDatabase;
import com.example.healthlink.EngagingHome.EngagingHomeActivity;
import com.example.healthlink.MainActivity;
import com.example.healthlink.R;

import java.util.ArrayList;

public class ProfileSettingActivity extends AppCompatActivity {
    public static User user;
    private LinearLayout containerLayout;
    private ConstraintLayout previousButton;

    private AppCompatButton nextButton;
    private LinearLayout stepIndicatorsLayout;

    private int currentStep = 0;
    private View[] steps;
    private TextView[] stepIndicators;

    public ProfileSettingActivity() {
    }

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);


        containerLayout = findViewById(R.id.container_layout);
        previousButton = findViewById(R.id.previous_button);
        nextButton = findViewById(R.id.next_button);
        stepIndicatorsLayout = findViewById(R.id.step_indicators_layout);


        // Initializing steps (views)
        steps = new View[]{
                LayoutInflater.from(this).inflate(R.layout.layout_general_info, containerLayout, false),
                LayoutInflater.from(this).inflate(R.layout.layout_contact_info, containerLayout, false),
                LayoutInflater.from(this).inflate(R.layout.layout_medical_info, containerLayout, false)
        };

        // Initializing step indicators (circles and arrows)
        initializeStepIndicators();


        showCurrentStep();

        // Setting click listener for previous button
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep > 0) {
                    currentStep--;
                    showCurrentStep();
                }
            }
        });

        // Setting click listener for next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep < steps.length - 1) {
                    currentStep++;
                    showCurrentStep();
                } else {
                    // When Last step reached submit the form
                    submitForm();
                }
            }
        });


    }

    // Initializing step indicators with circles and arrows
    private void initializeStepIndicators() {
        stepIndicators = new TextView[steps.length];


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );


        params.setMargins(20, 0, 20, 0);
        for (int i = 0; i < steps.length; i++) {
            TextView stepIndicator = new TextView(this);
            stepIndicator.setText(String.valueOf( i + 1));
            stepIndicator.setTextColor(Color.WHITE);
            stepIndicator.setTextSize(18);
            stepIndicator.setBackgroundResource(R.drawable.onboarding_indicator_inactive);
            stepIndicator.setGravity(Gravity.CENTER);
            stepIndicator.setPadding(35,20,35,20);
            stepIndicator.setLayoutParams(params);
            stepIndicatorsLayout.addView(stepIndicator);
            stepIndicators[i] = stepIndicator;

            if (i < steps.length - 1) {
                addArrowIndicator(stepIndicatorsLayout);
            }
        }
    }

    // Adding arrow indicator between step indicators
    private void addArrowIndicator(LinearLayout stepIndicatorsLayout) {
        ImageView arrow = new ImageView(this);
        // to add this create a new drawable resource file in res->drawable
        arrow.setImageResource(R.drawable.back);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.CENTER_VERTICAL;
        arrow.setLayoutParams(params);
        stepIndicatorsLayout.addView(arrow);
    }

    // Showing the current step
    private void showCurrentStep() {
        containerLayout.removeAllViews();
        containerLayout.addView(steps[currentStep]);
        // If Current Step is greater then 0 then making Previous Button Visible
        previousButton.setEnabled(currentStep == 0 ? false:true);
//        previousButton.setVisibility(currentStep > 0 ? View.VISIBLE: View.INVISIBLE);

        nextButton.setText(currentStep < steps.length - 1 ? "Next" : "Submit");

        updateStepIndicators();
    }

    // Updating the step indicators to highlight the current step
    private void updateStepIndicators() {
        for (int i = 0; i < stepIndicators.length; i++) {
            if (i == currentStep) {
                stepIndicators[i].setBackgroundResource(R.drawable.onboarding_indicator_active);
            } else {
                stepIndicators[i].setBackgroundResource(R.drawable.onboarding_indicator_inactive);
            }
        }
    }

    // When clicked on submit button at last form/activity
    private void submitForm() {

        // Initialize the view elements for the current step
        View generalLayout = steps[0];
        View contactLayout = steps[1];
        View medicalLayout = steps[2];
        EditText editTextFirstName = generalLayout.findViewById(R.id.editTextFirstName);
        EditText editTextLastName = generalLayout.findViewById(R.id.editTextLastName);
        EditText editTextEmail = contactLayout.findViewById(R.id.editTextEmail);
        EditText editTextPhoneNumber = contactLayout.findViewById(R.id.editTextPhoneNumber);
        Spinner spinnerCountry = contactLayout.findViewById(R.id.spinnerCountry);
        EditText editTextMedicalHistory = medicalLayout.findViewById(R.id.editTextMedicalHistory);
        EditText editTextInsuranceInfo = medicalLayout.findViewById(R.id.editTextInsuranceInfo);
        EditText editTextBloodType = medicalLayout.findViewById(R.id.editTextBloodType);


        // Retrieve data from the user inputs and set it in the User object
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        String country = "Jordan";
        // Check if the spinner is not null and it has at least one item
        if (spinnerCountry != null && spinnerCountry.getCount() > 0) {country = spinnerCountry.getSelectedItem().toString();}
            String medicalHistory = editTextMedicalHistory.getText().toString();
            String insuranceInfo = editTextInsuranceInfo.getText().toString();
            String bloodType = editTextBloodType.getText().toString();

            // Create a User object to hold all the information
            user = new User(firstName, lastName, email, phoneNumber, country, medicalHistory, insuranceInfo, bloodType);
            Long newUserId = AppDatabase.getInstance(getApplicationContext()).userDao().insertUser(user);

            user.id = newUserId;
            if (user != null) {
                Log.i("DATABASE", AppDatabase.getInstance(getApplicationContext()).userDao().getUserByID(user.id).toString());
            } else {
                Log.e("DATABASE", "User object is null");
            }

        startActivity(new Intent(getApplicationContext(), EngagingHomeActivity.class));
        finish();


    }
    }

