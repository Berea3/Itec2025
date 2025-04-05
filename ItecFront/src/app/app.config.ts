import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import {securityInterceptor} from './services/security.interceptor';
import {provideNativeDateAdapter} from '@angular/material/core';

export const appConfig: ApplicationConfig = {
    providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes), provideHttpClient(withInterceptors([securityInterceptor])), provideNativeDateAdapter()]
};
