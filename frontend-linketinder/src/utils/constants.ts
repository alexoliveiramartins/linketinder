export const REGEX_NOME: RegExp = /^[A-Za-zÀ-ü\s]+$/;
export const REGEX_EMAIL: RegExp = /^[_\.\-0-9A-Za-zÀ-ü]+@\w+\.\w{2,}(\.\w{2,})?$/;
export const REGEX_CPF: RegExp = /^(\d{3}\.\d{3}\.\d{3}-\d{2}||\d{3}\d{3}\d{3}\d{2})$/;
export const REGEX_CEP: RegExp = /^(\d{5}-?\d{3})$/;
export const REGEX_CNPJ: RegExp = /^(\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}||\d{2}\d{3}\d{3}\d{4}\d{2})$/;