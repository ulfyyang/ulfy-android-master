package com.ulfy.master.domain.entity;

import java.util.List;

public class Movie {
    public String title;
    public String describtion;
    public List<Actor> actors;

    public static class Actor {
        public String name;
        public int age;

        @Override public String toString() {
            return "Actor{name='" + name + '\'' + ", age=" + age + '}';
        }
    }

    @Override public String toString() {
        return "Movie{title='" + title + '\'' + ", describtion='" + describtion + '\'' + ", actors=" + actors + '}';
    }
}
