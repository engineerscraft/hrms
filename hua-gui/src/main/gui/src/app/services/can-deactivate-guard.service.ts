import { Injectable }    from '@angular/core';
import { CanDeactivate } from '@angular/router';
import { Observable }    from 'rxjs/Observable';
import { EmployeeCreationComponent } from '../employee-creation/employee-creation.component';

export interface CanComponentDeactivate {
 canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

@Injectable()
export class CanDeactivateGuard implements CanDeactivate<EmployeeCreationComponent> {
  canDeactivate(component: CanComponentDeactivate) {
    console.log('Called!!');
    return component.canDeactivate ? component.canDeactivate() : true;
  }
}