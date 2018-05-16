package com.example.sangnv.appnews;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sangnv.appnews.RssGet.Item_RSS;
import com.example.sangnv.appnews.RssGet.XMLParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class RssItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_RSS_LINK = "link-Rss";
    // TODO: Customize parameters
    private String mTypeRSS = null;
    private OnListFragmentInteractionListener mListener;
    public List<Item_RSS> item_rssList;
    Context context;
    View mView;
    TextView textView;
    RecyclerView recyclerView;
    private ProgressDialog dialog;
    boolean isConnected = false;




    public SwipeRefreshLayout mSwipeRefreshLayout;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RssItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RssItemFragment newInstance(String linkRss) {
        RssItemFragment fragment = new RssItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_RSS_LINK, linkRss);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTypeRSS = getArguments().getString(ARG_RSS_LINK);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_rssitem_list, container, false);

        // Set the adapter cho RecyclerView

        context = mView.getContext();
        recyclerView = (RecyclerView) mView.findViewById(R.id.list);
        textView = mView.findViewById(R.id.txt_dlg);

        //duong ngan cach 2 item
        DividerItemDecoration itemDecor = new DividerItemDecoration(context, VERTICAL);
        recyclerView.addItemDecoration(itemDecor);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //check sate internet
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected){
            textView.setVisibility(View.GONE);
            new ReadXML().execute(mTypeRSS);
        } else {
            if (MainActivity.inDex == 4)
            {
                textView.setVisibility(View.GONE);
                new ReadXML().execute(mTypeRSS);
            }
            else
            {
                textView.setVisibility(View.VISIBLE);
                // Sang thêm dòng dưới
                recyclerView.setVisibility(View.INVISIBLE);
                textView.setText("Không có kết nối internet");
            }
        }

        mSwipeRefreshLayout = mView.findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //lien ket sang main activity
                mListener.onRefreshSwipe();
            }
        });

        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Item_RSS item);
        //sang them ham xu ly refresh
        void onRefreshSwipe();
        //sang them ham xu ly click button xoa item
        void onClickButton();
    }

    public class ReadXML extends AsyncTask<String, Integer, List<Item_RSS>> {
        @Override
        protected void onPreExecute() {
            Toast.makeText(context,"Loading...",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected List<Item_RSS> doInBackground(String... params) {

            if (params[0].equals("da_luu"))
            {
                return MainActivity.list_item_RSS_saved;
            }
            else {

                XMLParser parser = new XMLParser();
                List<Item_RSS> itemList = new ArrayList<>();
                String xml = parser.getXmlFromUrl(params[0]);
                Document doc = parser.getDomElement(xml);

                try {
                    NodeList nodeList = doc.getElementsByTagName("item");
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Item_RSS item = new Item_RSS();
                        Element e = (Element) nodeList.item(i);
                        try {
                            NodeList titleNode = e.getElementsByTagName("title");
                            Element titleElement = (Element) titleNode.item(0);
                            item.setTitle(titleElement.getFirstChild().getNodeValue());
                        } catch (Exception e1) {
                            Log.d("doInbackGround", "title Lỗi rồi\n");
                        }

                        try {
                            NodeList linkNode = e.getElementsByTagName("link");
                            Element linkElement = (Element) linkNode.item(0);
                            item.setLink(linkElement.getFirstChild().getNodeValue());
                        } catch (Exception e1) {
                            Log.d("doInbackGround", " link Lỗi rồi\n");
                        }

                        try {
                            NodeList pubDateNode = e.getElementsByTagName("pubDate");
                            Element pubDateElement = (Element) pubDateNode.item(0);
                            item.setDate(pubDateElement.getFirstChild().getNodeValue());
                        } catch (Exception e1) {
                            Log.d("doInbackGround", " pubDate Lỗi rồi\n");
                        }

                        try {
                            NodeList node = e.getElementsByTagName("description");
                            Element desElment = (Element) node.item(0);
                            String s = new String();
                            s = desElment.getFirstChild().getNodeValue().toString();
                            item.setDescription(s);
                        } catch (Exception e1) {
                            Log.d("doInbackGround", " description Lỗi rồi\n");
                        }
                        //Log.d("description item", s);
                        itemList.add(item);
                    }
                } catch (Exception e) {
                    Log.d("doInbackGround", "Lỗi rồi\n");
                }
                return itemList;
            }
        }

        @Override
        protected void onPostExecute(List<Item_RSS> itemList) {
            // Toast.makeText(ListRssActivity.this,""+itemList.get(0).getDescription(),Toast.LENGTH_LONG).show();
            if (mSwipeRefreshLayout.isRefreshing())
                mSwipeRefreshLayout.setRefreshing(false);

            recyclerView.setAdapter(new RecyclerViewAdapter(itemList, mListener));
        }

    }
}
