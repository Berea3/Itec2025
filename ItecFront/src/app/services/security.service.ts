import { Injectable } from '@angular/core';
import {User} from '../entities/User';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

    private user: User;

    constructor() { }

    isLoggedIn()
    {
        return true;
    }

    getUser()
    {
        // return sessionStorage.getItem("roles");
        return this.user;
    }

    getRole()
    {
        // console.log(sessionStorage.getItem("roles"));
        return sessionStorage.getItem("roles");
    }

    setUser(user: User)
    {
        this.user=user;
    }
}
