/*
 * Copyright 2016 requery.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.requery.android.example.app.model;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.requery.CascadeAction;
import io.requery.Column;
import io.requery.Entity;
import io.requery.ForeignKey;
import io.requery.Generated;
import io.requery.Index;
import io.requery.Key;
import io.requery.OneToMany;
import io.requery.OneToOne;
import io.requery.Persistable;
import io.requery.android.BindingResource;
import io.requery.android.example.app.Binding;
import io.requery.query.MutableResult;

@Entity
@BindingResource(Binding.BR_CLASS)
public abstract class Person extends BaseObservable implements Parcelable, Persistable {

    @Key
    @Generated
    public int id;

    @Bindable
    public String name;

    @Bindable
    @Index(name = "email_index")
    public String email;

    @Bindable
    public Date birthday;

    @Bindable
    public int age;

    @Bindable
    @ForeignKey
    @OneToOne
    public Address address;

    @OneToMany(mappedBy = "owner", cascade = {CascadeAction.DELETE, CascadeAction.SAVE})
    public MutableResult<Phone> phoneNumbers;

    @Bindable
    @Column(unique = true)
    public UUID uuid;

    @OneToMany(mappedBy = "owner", cascade = {CascadeAction.DELETE, CascadeAction.SAVE})
    public List<Phone> phoneNumberList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MutableResult<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(MutableResult<Phone> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public List<Phone> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<Phone> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }
}
