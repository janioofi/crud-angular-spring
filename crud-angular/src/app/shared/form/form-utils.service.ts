import { FormGroup, UntypedFormArray, UntypedFormControl, UntypedFormGroup } from '@angular/forms';
import { Injectable } from '@angular/core';

@Injectable({
	providedIn: 'root',
})
export class FormUtilsService {
	constructor() {}

	validadeAllFormFields(formGroup: UntypedFormArray | UntypedFormGroup) {
		Object.keys(formGroup.controls).forEach((field) => {
			const control = formGroup.get(field);

			if(control instanceof UntypedFormControl){
				control.markAsTouched({onlySelf: true});
			} else if (control instanceof FormGroup || control instanceof UntypedFormGroup) {
				control.markAsTouched({onlySelf: true});
				this.validadeAllFormFields(control);
			}
		});
	}

	getErrorMessage(formGroup: UntypedFormGroup, fieldName: string) {
		const field = formGroup.get(fieldName) as UntypedFormControl;
		return this.getErrorMessageFromField(field);
	}

	getErrorMessageFromField(field: UntypedFormControl) {
		if (field?.hasError('required')) {
			return 'Campo Obrigatório';
		}

		if (field?.hasError('minlength')) {
			const requiredLength = field.errors
				? field.errors['minlength']['requiredLength']
				: 3;
			return `Tamanho minímo é ${requiredLength} caracteres`;
		}

		if (field?.hasError('maxlength')) {
			const requiredLength = field.errors
				? field.errors['maxlength']['requiredLength']
				: 100;
			return `Tamanho máximo é ${requiredLength} caracteres`;
		}

		if (field?.hasError('min')) {
			return `Mínimo 1h`;
		}

		if (field?.hasError('max')) {
			return `Máximo 500h`;
		}

		return 'Campo Inválido';
	}

	getFormArrayFieldErrorMessage(formGroup: UntypedFormGroup, formArrayName: string, fiedlName: string, index: number) {
		const formArray = formGroup.get(formArrayName) as UntypedFormGroup;
		const fiel = formArray.controls[index].get(fiedlName) as UntypedFormControl;
		return this.getErrorMessageFromField(fiel);
	}

	isFormArrayRequired(formGroup: UntypedFormGroup, formArrayName: string) {
		const formArray = formGroup.get(formArrayName) as UntypedFormArray;
		return !formArray.invalid && formArray.hasError('required') && formArray.touched;
	}
}
