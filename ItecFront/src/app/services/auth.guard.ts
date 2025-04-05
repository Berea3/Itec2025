import { CanActivateFn } from '@angular/router';
import {SecurityService} from './security.service';
import {inject} from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
    const securityService=inject(SecurityService);
    if (securityService.isLoggedIn()) return true;
    else router.navigateByUrl("/login");
    return false;
};
