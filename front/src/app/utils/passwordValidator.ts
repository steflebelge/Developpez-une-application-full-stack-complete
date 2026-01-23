import { Validators, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

// Custom validator pour mot de passe
export function passwordValidatorPerso(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value;
    if (!value) {
      return null; // facultatif
    }

    const minLength = value.length >= 8;
    const hasLowercase = /[a-z]/.test(value);
    const hasUppercase = /[A-Z]/.test(value);
    const hasDigit = /\d/.test(value);
    const hasSpecial = /[^A-Za-z0-9]/.test(value);

    const valid = minLength && hasLowercase && hasUppercase && hasDigit && hasSpecial;

    return valid ? null : {
      invalidPassword: {
        message: 'Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial'
      }
    };
  };
}
