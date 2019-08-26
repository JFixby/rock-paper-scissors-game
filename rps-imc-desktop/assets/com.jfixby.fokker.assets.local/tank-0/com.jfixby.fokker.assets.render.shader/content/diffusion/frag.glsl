#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP 
#endif
varying LOWP vec4 vColor;
varying vec2 vTexCoord;

uniform sampler2D u_texture_0_current; //
uniform sampler2D u_texture_1_original;
uniform sampler2D u_texture_2_alpha;

//resolution of screen or buffer dimensions
uniform float screen_width;
uniform float screen_height;

uniform float alpha_blend;

float luma3(vec3 color) {
	float gray = dot(color.rgb, vec3(0.299, 0.587, 0.114));
	return gray;
}

float luma4(vec4 color) {
	float gray = dot(color.rgb, vec3(0.299, 0.587, 0.114));
	return gray;
}

float dither8x8(vec2 position, float brightness) {
	int x = int(mod(position.x, 8.0));
	int y = int(mod(position.y, 8.0));
	int index = x + y * 8;
	float limit = 0.0;

	if (x < 8) {
		if (index == 0)
			limit = 0.015625;
		if (index == 1)
			limit = 0.515625;
		if (index == 2)
			limit = 0.140625;
		if (index == 3)
			limit = 0.640625;
		if (index == 4)
			limit = 0.046875;
		if (index == 5)
			limit = 0.546875;
		if (index == 6)
			limit = 0.171875;
		if (index == 7)
			limit = 0.671875;
		if (index == 8)
			limit = 0.765625;
		if (index == 9)
			limit = 0.265625;
		if (index == 10)
			limit = 0.890625;
		if (index == 11)
			limit = 0.390625;
		if (index == 12)
			limit = 0.796875;
		if (index == 13)
			limit = 0.296875;
		if (index == 14)
			limit = 0.921875;
		if (index == 15)
			limit = 0.421875;
		if (index == 16)
			limit = 0.203125;
		if (index == 17)
			limit = 0.703125;
		if (index == 18)
			limit = 0.078125;
		if (index == 19)
			limit = 0.578125;
		if (index == 20)
			limit = 0.234375;
		if (index == 21)
			limit = 0.734375;
		if (index == 22)
			limit = 0.109375;
		if (index == 23)
			limit = 0.609375;
		if (index == 24)
			limit = 0.953125;
		if (index == 25)
			limit = 0.453125;
		if (index == 26)
			limit = 0.828125;
		if (index == 27)
			limit = 0.328125;
		if (index == 28)
			limit = 0.984375;
		if (index == 29)
			limit = 0.484375;
		if (index == 30)
			limit = 0.859375;
		if (index == 31)
			limit = 0.359375;
		if (index == 32)
			limit = 0.0625;
		if (index == 33)
			limit = 0.5625;
		if (index == 34)
			limit = 0.1875;
		if (index == 35)
			limit = 0.6875;
		if (index == 36)
			limit = 0.03125;
		if (index == 37)
			limit = 0.53125;
		if (index == 38)
			limit = 0.15625;
		if (index == 39)
			limit = 0.65625;
		if (index == 40)
			limit = 0.8125;
		if (index == 41)
			limit = 0.3125;
		if (index == 42)
			limit = 0.9375;
		if (index == 43)
			limit = 0.4375;
		if (index == 44)
			limit = 0.78125;
		if (index == 45)
			limit = 0.28125;
		if (index == 46)
			limit = 0.90625;
		if (index == 47)
			limit = 0.40625;
		if (index == 48)
			limit = 0.25;
		if (index == 49)
			limit = 0.75;
		if (index == 50)
			limit = 0.125;
		if (index == 51)
			limit = 0.625;
		if (index == 52)
			limit = 0.21875;
		if (index == 53)
			limit = 0.71875;
		if (index == 54)
			limit = 0.09375;
		if (index == 55)
			limit = 0.59375;
		if (index == 56)
			limit = 1.0;
		if (index == 57)
			limit = 0.5;
		if (index == 58)
			limit = 0.875;
		if (index == 59)
			limit = 0.375;
		if (index == 60)
			limit = 0.96875;
		if (index == 61)
			limit = 0.46875;
		if (index == 62)
			limit = 0.84375;
		if (index == 63)
			limit = 0.34375;
	}

	return brightness < limit ? 0.0 : 1.0;
}

vec3 dither8x8(vec2 position, vec3 color) {
	return color * dither8x8(position, luma3(color));
}

vec4 dither8x8(vec2 position, vec4 color) {
	return vec4(color.rgb * dither8x8(position, luma4(color)), 1.0);
}

float softLightC(float blend, float base) {
	float result = 0.0;
	if (blend < 0.5) {
		result = 2.0 * base * blend + base * base - 2.0 * base * base * blend;
	} else {
		result = 2.0 * sqrt(base) * blend - sqrt(base) + 2.0 * base
				- 2.0 * base * blend;
	}
	return result;
}

vec4 softLight(vec4 blend, vec4 base) {
	vec4 result = vec4(0.0, 0.0, 0.0, 0.0);
	result.r = softLightC(blend.r, base.r);
	result.g = softLightC(blend.g, base.g);
	result.b = softLightC(blend.b, base.b);
	result.a = blend.a;
	return result;
}

float mixC(float newColor, float olfColor, float mix) {
	return newColor * mix + olfColor * (1.0 - mix);
}

vec4 mixColor(vec4 newColor, vec4 olfColor, float mix) {
	vec4 result = vec4(0, 0, 0, 0);
	result.r = mixC(newColor.r, olfColor.r, mix);
	result.g = mixC(newColor.g, olfColor.g, mix);
	result.b = mixC(newColor.b, olfColor.b, mix);
	result.a = newColor.a;
	return result;
}

void main() {
	vec4 currentTextureColor = texture2D(u_texture_0_current, vTexCoord);
	vec4 alphaColor = texture2D(u_texture_2_alpha, vTexCoord);

	vec2 resolution = vec2(screen_width, screen_height);
	vec2 originalCoord = (gl_FragCoord.xy / resolution.xy);
	vec4 originalColor = texture2D(u_texture_1_original, originalCoord);

	vec4 finalColor = originalColor;

	finalColor.a = alphaColor.a * currentTextureColor.a * alpha_blend;
	finalColor.b = finalColor.r;

	finalColor = dither8x8(gl_FragCoord.xy, originalColor) * 2.0;

	finalColor = softLight(finalColor, originalColor);

	finalColor = mixColor(finalColor, originalColor, 0.5);

	gl_FragColor = finalColor;
}

