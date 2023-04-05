package com.repairshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.repairshop.amit.R;

import java.util.ArrayList;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {

    private ArrayList<vehical> vehicals;

    public VehicleAdapter(ArrayList<vehical> vehicals) {
        this.vehicals = vehicals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        vehical model = vehicals.get(position);
        holder.tvVehicleName.setText(model.getName());
        holder.tvVehicleType.setText(model.getVehicleTypeName());
        holder.tvMake.setText(model.getMakeName());
        holder.tvRepairShop.setText(model.getRepairShopName());
    }

    @Override
    public int getItemCount() {
        return vehicals.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvVehicleName, tvVehicleType, tvMake, tvRepairShop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVehicleName = itemView.findViewById(R.id.tv_vehicle_name);
            tvVehicleType = itemView.findViewById(R.id.tv_vehicle_type);
            tvMake = itemView.findViewById(R.id.tv_make);
            tvRepairShop = itemView.findViewById(R.id.tv_repair_shop);
        }
    }
}
