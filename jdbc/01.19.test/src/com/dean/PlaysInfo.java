package com.dean;

import java.util.Objects;

public class PlaysInfo {
    private Film film;
    private Cinema cinema;

    public boolean equals(Object o){
        if (this==o) return true;
        if (o == null || getClass()!=o.getClass())
            return false;
        PlaysInfo playsInfo = (PlaysInfo) o;
        return Objects.equals(film,playsInfo.cinema) &&
                Objects.equals(cinema,playsInfo.cinema);
    }
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public PlaysInfo() {
    }

    public PlaysInfo(Film film, Cinema cinema) {
        this.film = film;
        this.cinema = cinema;
    }
}
