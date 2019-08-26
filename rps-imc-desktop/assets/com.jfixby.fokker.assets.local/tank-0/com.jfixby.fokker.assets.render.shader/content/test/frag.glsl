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

void main() {
	vec4 currentTextureColor = texture2D(u_texture_0_current, vTexCoord);
	vec4 alphaColor = texture2D(u_texture_2_alpha, vTexCoord);

	vec2 resolution = vec2(screen_width, screen_height);
	vec2 originalCoord = (gl_FragCoord.xy / resolution.xy);
	vec4 originalColor = texture2D(u_texture_1_original, originalCoord);

	vec4 finalColor = currentTextureColor;

	finalColor.a = alphaColor.a * currentTextureColor.a * alpha_blend;
	gl_FragColor = finalColor;
}
