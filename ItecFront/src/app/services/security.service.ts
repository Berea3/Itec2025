import { Injectable } from '@angular/core';
import {User} from '../entities/User';
import {HttpClient} from '@angular/common/http';
import {LinkService} from './link.service';
import {Auth} from '../entities/Auth';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

    private user: User;
    private auth: Auth;
    private loggedIn: boolean=true;

    constructor(private http: HttpClient, private link: LinkService) {}

    ngOnInit()
    {
        this.http.get(this.link.url+"/security/loggedin").subscribe(
            (response: any)=>{
                this.auth=response;
            }
        );
    }

    isLoggedIn()
    {
        return this.loggedIn;
        // if (this.auth!=undefined) if (this.auth.loggedin!=null) return true;
        // return false;
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

    checkLoggedIn()
    {
        this.http.get(this.link.url+"/security/loggedin").subscribe(
            (response: any)=>{
                this.auth=response;
            }
        );
    }
}
