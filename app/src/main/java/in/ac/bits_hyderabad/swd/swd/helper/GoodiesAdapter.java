package in.ac.bits_hyderabad.swd.swd.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.ac.bits_hyderabad.swd.swd.APIConnection.Goodie;
import in.ac.bits_hyderabad.swd.swd.R;

public class GoodiesAdapter extends BaseAdapter {

    private ArrayList<Goodie> goodies;
    itemClicked activity;
    private LayoutInflater mInflater;
    private Context mContext;

    public GoodiesAdapter(Context context, Fragment fragment, ArrayList<Goodie> goodies) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.goodies = goodies;
        activity = (itemClicked) fragment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mInflater.inflate(R.layout.card_goodies, parent, false);

        String ImageUrl = "http://swd.bits-hyderabad.ac.in/goodies/img/" + goodies.get(position).getImg();//"All.jpg"  or   goodies.get(i).getImage()

        TextView tvGoodieName = convertView.findViewById(R.id.tvGoodieName);
        ImageView ivGoodie = convertView.findViewById(R.id.ivGoodie);
        TextView tvGoodiePrice = convertView.findViewById(R.id.tvGoodiePrice);
        TextView tvGoodieHost = convertView.findViewById(R.id.tvGoodieHost);

        Picasso.get().load(ImageUrl)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .fit().centerInside()
                .into(ivGoodie);

        tvGoodieName.setText(goodies.get(position).getName());
        tvGoodieHost.setText(goodies.get(position).getHosted_by());
        String priceToDisplay = "₹" + goodies.get(position).getPrice();
        tvGoodiePrice.setText(priceToDisplay);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onItemClicked(position);
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return goodies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public interface itemClicked {
        void onItemClicked(int index);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
