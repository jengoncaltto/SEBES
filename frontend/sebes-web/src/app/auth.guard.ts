import { CanActivateFn, ActivatedRouteSnapshot, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '@services/auth.service';

export const AuthGuard: CanActivateFn = (route: ActivatedRouteSnapshot) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const requireLogin = route.data['requireLogin'];
  const loggedOut = route.data['loggedOut'];
  const allowedRoles = route.data['roles'] as String[] | undefined;

  if (requireLogin || allowedRoles) {
    if (!authService.isLoggedIn()) {
      router.navigate(['/login']);
      return false;
    }
  }
  
if (loggedOut) {
    if (authService.isLoggedIn()) {
      router.navigate(['/perfil']);
      return false;
    }
  }

  if (allowedRoles) {
    const tipo = authService.getTipo();
    if (tipo == null || !allowedRoles.includes(tipo)) {
      router.navigate(['/acesso-negado']);
      return false;
    }
  }

  return true;
};
