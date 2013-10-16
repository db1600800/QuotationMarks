package com.compoment.jsonToJava.creater;

import java.util.List;
public class Bean{
public List<ProgrammersBean> programmers;
public class ProgrammersBean{
public String firstName;
public String lastName;
public String email;
}
public List<AuthorsBean> authors;
public class AuthorsBean{
public String firstName;
public String lastName;
public String genre;
}
public List<MusiciansBean> musicians;
public class MusiciansBean{
public String firstName;
public String lastName;
public String instrument;
}
}