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

package io.requery.android.example.app;

import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.Callable;

import io.requery.Persistable;
import io.requery.android.example.app.model.AddressEntity;
import io.requery.android.example.app.model.PersonEntity;
import io.requery.rx.SingleEntityStore;
import rx.Observable;

class CreatePeople implements Callable<Observable<Iterable<PersonEntity>>> {

    private final SingleEntityStore<Persistable> data;

    CreatePeople(SingleEntityStore<Persistable> data) {
        this.data = data;
    }

    @Override
    public Observable<Iterable<PersonEntity>> call() {
        String[] firstNames = new String[]{
                "Alice", "Bob", "Carol", "Chloe", "Dan", "Emily", "Emma", "Eric", "Eva",
                "Frank", "Gary", "Helen", "Jack", "James", "Jane",
                "Kevin", "Laura", "Leon", "Lilly", "Mary", "Maria",
                "Mia", "Nick", "Oliver", "Olivia", "Patrick", "Robert",
                "Stan", "Vivian", "Wesley", "Zoe"};
        String[] lastNames = new String[]{
                "Hall", "Hill", "Smith", "Lee", "Jones", "Taylor", "Williams", "Jackson",
                "Stone", "Brown", "Thomas", "Clark", "Lewis", "Miller", "Walker", "Fox",
                "Robinson", "Wilson", "Cook", "Carter", "Cooper", "Martin"};
        Random random = new Random();

        final Set<PersonEntity> people = new TreeSet<>((Comparator<PersonEntity>) new Comparator<PersonEntity>() {
            @Override
            public int compare(PersonEntity lhs, PersonEntity rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        // creating many people (but only with unique names)
        for (int i = 0; i < 3000; i++) {
            PersonEntity person = new PersonEntity();
            String first = firstNames[random.nextInt(firstNames.length)];
            String last = lastNames[random.nextInt(lastNames.length)];
            person.setName(first + " " + last);
            person.setUUID(UUID.randomUUID());
            person.setEmail(Character.toLowerCase(first.charAt(0)) +
                    last.toLowerCase() + "@gmail.com");
            AddressEntity address = new AddressEntity();
            address.setLine1("123 Market St");
            address.setZip("94105");
            address.setCity("San Francisco");
            address.setState("CA");
            address.setCountry("US");
            person.setAddress(address);
            people.add(person);
        }
        return data.insert(people).toObservable();
    }
}
