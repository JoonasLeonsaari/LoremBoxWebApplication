"use strict";

// below are the neccesary functions for password hashing
// if you want to hash pass you must have the forms onSubmit="hashPass();"
const convertStringToArrayBufferView = (str) => {
	let bytes = new Uint8Array(str.length);
	for (let iii = 0; iii < str.length; iii++) {
		bytes[iii] = str.charCodeAt(iii);
	}

	return bytes;
};

const convertArrayBufferToHexaDecimal = (buffer) => {
	let data_view = new DataView(buffer);
	let iii, len, hex = '',
		c;

	for (iii = 0, len = data_view.byteLength; iii < len; iii += 1) {
		c = data_view.getUint8(iii).toString(16);
		if (c.length < 2) {
			c = '0' + c;
		}

		hex += c;
	}

	return hex;
};

const hashSign = () => {

	let pass = passLogin.value;
	const crypto = window.crypto || window.msCrypto;

	if (crypto.subtle) {
		alert("Cryptography API Supported");

		const promise = crypto.subtle.digest({
			name: "SHA-256"
		}, convertStringToArrayBufferView(pass));

		promise.then(function (result) {
			const hash_value = convertArrayBufferToHexaDecimal(result);
			console.log("hash: " + hash_value)
			passLogin.value = hash_value;
      console.log(password.value);
		});
	} else {
		alert("Cryptography API not Supported");
	}
};

const hashLogin = () => {

	let pass = passSign.value;
	const crypto = window.crypto || window.msCrypto;

	if (crypto.subtle) {
		alert("Cryptography API Supported");

		const promise = crypto.subtle.digest({
			name: "SHA-256"
		}, convertStringToArrayBufferView(pass));

		promise.then(function (result) {
			const hash_value = convertArrayBufferToHexaDecimal(result);
			console.log("hash: " + hash_value)
			passSign.value = hash_value;
      console.log(password.value);
		});
	} else {
		alert("Cryptography API not Supported");
	}
};
