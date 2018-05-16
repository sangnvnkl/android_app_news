package com.example.sangnv.appnews;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sangnv.appnews.RssGet.Item_RSS;
import com.example.sangnv.appnews.RssItemFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Item_RSS> mValues;
    private final OnListFragmentInteractionListener mListener;

    public RecyclerViewAdapter(List<Item_RSS> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_rssitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.imageButton.setBackground(holder.mView.getBackground());

        holder.mItem = mValues.get(position);

        if(mValues.get(position).isSaved())
            holder.imageButton.setImageResource(R.drawable.ic_favorite);
        else
            holder.imageButton.setImageResource(R.drawable.ic_favorite_border);

        if (holder.mItem.isNull()) {
            holder.mTitle.setText("Link bài báo bị lỗi!");
            holder.mDate.setText(null);
            holder.imageView.setImageResource(R.drawable.ic_error);
        } else {
            try {
                holder.mTitle.setText(mValues.get(position).getTitle());
                holder.mDate.setText(mValues.get(position).getDate());
                if (!holder.mItem.getDescription().isEmpty())
                    Picasso.get().load(holder.mItem.getDescription()).into(holder.imageView);
            } catch (Exception e){
                Log.e("Bind adapter" , "Error");
            }
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
        /// viet
        /// xu ly khi an luu va bo luu
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mValues.get(position).isSaved())
                {
                    holder.imageButton.setImageResource(R.drawable.ic_favorite_border);
                    mValues.get(position).setSaved(false);
                    holder.mItem.setSaved(false);
                    if (MainActivity.list_item_RSS_saved != null)
                    {
                        MainActivity.list_item_RSS_saved.remove(mValues.get(position));
                        //update adapter fix lỗi crash
                        if (MainActivity.inDex == 4)
                            //chi refresh trong tab da luu
                            mListener.onClickButton();
                    }

                } else {
                    holder.imageButton.setImageResource(R.drawable.ic_favorite);
                    mValues.get(position).setSaved(true);
                    holder.mItem.setSaved(true);
                    if (MainActivity.list_item_RSS_saved != null)
                    {
                        //sang sua xu ly luu 2 lan
                        Stream<Item_RSS> rssStream = MainActivity.list_item_RSS_saved.stream();
                        Optional<Item_RSS> stringOptional = rssStream
                                .parallel()
                                .filter(b -> b.getTitle().equals(holder.mTitle.getText().toString()))
                                .findAny();

						if (!stringOptional.isPresent()) {
							MainActivity.list_item_RSS_saved.add(0, holder.mItem);
						}   
                    }
                }
            }
        });
        ///
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final ImageView imageView;
        public final TextView mDate;
        public Item_RSS mItem;

        /// Viet
        public final ImageButton imageButton;
        public boolean isSaved = false;
        ///

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.txt_rss_title);
            mDate = (TextView) view.findViewById(R.id.txt_rss_date);
            imageView = view.findViewById(R.id.imageView);

            /// viet
            imageButton = (ImageButton) view.findViewById(R.id.imbtn_save);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}
