package com.example.dell.FirebaseChat.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.FirebaseChat.Model.Chat;
import com.example.dell.FirebaseChat.Model.User;
import com.example.dell.FirebaseChat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
/*
A może zrobić Singletona co będzie pobierał adres no i przekazywał tutaj? Pomiędzy activity jako Intent będzie przekazywał.. spróbuj..
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context mContext;
    private List<Chat> mChat;
    private String imageurl;
    private String mojObrazek;

    FirebaseUser fuser;

    Uri photoUrl;
    String adresik;

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    public MessageAdapter(Context mContext, List<Chat> mChat, String imageurl) {
        this.mContext = mContext;
        this.mChat = mChat;
        this.imageurl = imageurl;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView show_message;
        public ImageView profil_image;

        private ImageView img_on;
        private ImageView img_off;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
            profil_image = itemView.findViewById(R.id.profile_image);


        }
    }


    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, viewGroup, false);
            return new MessageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, viewGroup, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull final MessageAdapter.ViewHolder viewHolder, int i) {

        Chat chat = mChat.get(i);
        viewHolder.show_message.setText(chat.getMessage());
        if (chat.getSender().equals(fuser.getUid())) { //źle

            if ( adresik =="default") {viewHolder.profil_image.setImageResource(R.mipmap.ic_launcher);
            } else {
                Glide.with(mContext).load(adresik    ).into(viewHolder.profil_image);
            }
        } else { // dobrze
            if (imageurl.equals("default")) {
                viewHolder.profil_image.setImageResource(R.mipmap.ic_launcher);
            } else {
                Glide.with(mContext).load(imageurl).into(viewHolder.profil_image);
            }
//        }

        }

    }
        @Override
        public int getItemCount () {
            return mChat.size();
        }


        @Override
        public int getItemViewType ( int position){
            fuser = FirebaseAuth.getInstance().getCurrentUser();
            if (mChat.get(position).getSender().equals(fuser.getUid())) {
                return MSG_TYPE_RIGHT;
            } else {
                return MSG_TYPE_LEFT;
            }

        }

//    public rightItem(){
//        reference = FirebaseDatabase.getInstance().getReference("users").child(fuser.getUid());
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                if(user.getImageURL().equals("default")){
////                        mojObrazek = 'null";
//                } else {
//                     mojObrazek = user.getImageURL();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    }


