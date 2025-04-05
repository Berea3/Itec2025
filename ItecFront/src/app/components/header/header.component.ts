import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {SecurityService} from '../../services/security.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-header',
    imports: [
        RouterLink,
        NgIf
    ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

    constructor(private securityService: SecurityService) {}

    isLoggedIn()
    {
        return this.securityService.isLoggedIn();
    }
}
