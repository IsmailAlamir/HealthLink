package com.example.healthlink.Doctors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthlink.EngagingHome.Trend;
import com.example.healthlink.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    List <Doctor> doctors;

    public DoctorAdapter(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_doctor,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {
        holder.setViewHolderData(doctors.get(position));


    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, specialization, address, phone ;
        private ImageView imgSrc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_doctor_name);
            specialization= itemView.findViewById(R.id.tv_doctor_specialization);
            address = itemView.findViewById(R.id.tv_doctor_address);
            phone = itemView.findViewById(R.id.tv_doctor_phone_number);
            imgSrc = itemView.findViewById(R.id.iv_doctor_pic);

        }

        void setViewHolderData(Doctor doctor){
            name.setText(doctor.getName());
            specialization.setText(doctor.getSpecialization());
            address.setText(doctor.getAddress());
            phone.setText(doctor.getPhone());
            Picasso.get().load(doctor.getImgSrc()).into(imgSrc);

        }
    }
}
