import { FormControl } from '@angular/forms';

export class CustomValidator {
    static noSpace(control: FormControl) {
        if (control.value.indexOf(' ') >= 0)
            return {
                cannotContainSpace: true
            };
        return null;
    }
}
