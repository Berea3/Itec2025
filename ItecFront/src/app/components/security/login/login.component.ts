import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {LinkService} from '../../../services/link.service';
import {SecurityService} from '../../../services/security.service';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {User} from '../../../entities/User';

@Component({
  selector: 'app-login',
    imports: [
        FormsModule
    ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
    email: string;
    password: string;

    user: User=new User();

    constructor(private http: HttpClient, private router: Router, private securityService: SecurityService, private link: LinkService) {}

    onSubmit()
    {
        const formData : FormData=new FormData();
        formData.append('username',this.email);
        formData.append('password',this.password);
        formData.append('rememberMe',"true");
        this.http.post(this.link.url+'/login',formData).subscribe(
            (response: any)=>{

                this.user=response;
                if (this.user.id==null)
                {
                    alert("wrong credentials");
                }
                else
                {
                    this.securityService.checkLoggedIn();
                    this.securityService.setUser(this.user);
                    this.router.navigateByUrl("");
                }
            }
        );
    }
}
