import { FormControl } from '@angular/forms';

export class CustomValidator {
    static noSpace(control: FormControl) {
        if (control.value.indexOf(' ') >= 0)
            return {
                cannotContainSpace: true
            };
        return null;
    }

    static properDate(control: FormControl) {
        let datePattern = /^(0[1-9]|1\d|2\d|3[01])\/(0[1-9]|1[0-2])\/(19|20)\d{2}$/;

        if (!control.value.match(datePattern)) {
            return { "inValidDate": true };
        }
        return null;
    }

    static validEmail(control: FormControl) {
        var pattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (!control.value.match(pattern)) {
            return { "inValidEmail": true };
        }
        return null;
    }
}
