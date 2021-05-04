package com.fjsy.architecture.utils;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.fjsy.architecture.global.data.bean.MobileContactsBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Czm on 2019/6/21.
 * 获取手机通讯录
 */

public class ContactUtils {
    public static ArrayList<MobileContactsBean> getAllContacts() {
        ArrayList<MobileContactsBean> contacts = new ArrayList<>();

        Cursor cursor = Utils.getApp().getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (Objects.requireNonNull(cursor).moveToNext()) {
            //新建一个联系人实例
            MobileContactsBean temp = new MobileContactsBean();
            String contactId = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts._ID));
            //获取联系人姓名
            String name = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            temp.setNickname(name);

            List<String> phoneList = new ArrayList<>();
            //获取联系人电话号码
            Cursor phoneCursor = Utils.getApp().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            while (Objects.requireNonNull(phoneCursor).moveToNext()) {
                String phone = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phone = phone.replace("-", "");
                phone = phone.replace(" ", "");
                if (phone.startsWith("1")||phone.startsWith("+86")){
                    if (!phone.startsWith("10")){
                        temp.setMobile(phone);
                        //一个联系人有多个手机号码
                        phoneList.add(phone);
                    }
                }
            }


            //获取联系人备注信息
            Cursor noteCursor = Utils.getApp().getContentResolver().query(
                    ContactsContract.Data.CONTENT_URI,
                    new String[]{ContactsContract.Data._ID, ContactsContract.CommonDataKinds.Nickname.NAME},
                    ContactsContract.Data.CONTACT_ID + "=?" + " AND " + ContactsContract.Data.MIMETYPE + "='"
                            + ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE + "'",
                    new String[]{contactId}, null);

            if (Objects.requireNonNull(noteCursor).moveToFirst()) {
                do {
                    String note = noteCursor.getString(noteCursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Nickname.NAME));
                    temp.setNote(note);
                } while (noteCursor.moveToNext());
            }

            //添加当前通讯录实体到数组
            contacts.add(temp);
            //当一个用户有多个手机号时
            if (phoneList.size()>1){
                for (int i = 0 ; i < phoneList.size(); i++){
                    if (!temp.getMobile().equals(phoneList.get(i))){
                        MobileContactsBean othersPhones = new MobileContactsBean();
                        othersPhones.setNote(temp.getNote());
                        othersPhones.setNickname(temp.getNickname());
                        othersPhones.setMobile(phoneList.get(i));
                        contacts.add(othersPhones);
                    }
                }
            }

            //记得要把cursor给close掉
            phoneCursor.close();
            noteCursor.close();
        }
        cursor.close();
        return contacts;
    }
}
