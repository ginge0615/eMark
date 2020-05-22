import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {UserService} from '../services/user.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private userService: UserService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.userService.currentUserToken;
    console.log('token:' + token);
    if (token && token.length > 0) {
      request = request.clone({
        setHeaders: {
          Authorization: "Bearer " + token
        }
      });
    }

    return next.handle(request);
  }
}
