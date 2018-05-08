package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserBD extends ForwardingSet<UserData> {

    private Set<UserData> delegate;

    public UserBD(UserBD userBD) {
        this.delegate = new HashSet<UserData>(userBD.delegate);
    }

    public UserBD() {
        this.delegate = new HashSet<UserData>();
    }

    public UserBD(Collection<UserData> userBD) {
        this.delegate = new HashSet<UserData>(userBD);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }
}