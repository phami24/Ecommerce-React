/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {},
    fontFamily: {
      mar: ["Marcellus"],
      san: ["DM Sans", "sans-serif"],
      marsf: ["Marcellus,serif"],
      roboto:["Roboto Slab","sans-serif"],
      meadowbrook:["Meadowbrook"],
      inherit: ["inherit"],
      backgroundImage: {
        "hero-pattern": "url('https://images2.thanhnien.vn/uploaded/nthanhluan/2015_05_27/1_MQQT.jpg?width=500')",
      },
    },
  },

  plugins: [
    function ({ addUtilities }) {
      const cubicBezierUtilities = {
        ".ease-cubic-bezier": {
          "transition-timing-function": "cubic-bezier(.215,.61,.355,1)",
        },
      };
      addUtilities(cubicBezierUtilities);
    },
  ],
};
