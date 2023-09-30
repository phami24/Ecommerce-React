import { Grid } from "@mui/material";
import EastIcon from "@mui/icons-material/East";
import flowerIcon from "../../../../src/assets/images/flower.png";
import treeIcon from "../../../../src/assets/images/tree.png";
import bonsaiIcon from "../../../../src/assets/images/bonsai.png";
import parkIcon from "../../../../src/assets/images/park.png";
import cactusIcon from "../../../../src/assets/images/cactus.png";
import medicationIcon from "../../../../src/assets/images/medication.png";

function HomeCategories() {
  const imagePaths = [
    flowerIcon,
    treeIcon,
    bonsaiIcon,
    parkIcon,
    cactusIcon,
    medicationIcon,
  ];

  return (
    <div>
      <div className="w-full mt-24 px-4 md:px-20 lg:px-40 flex justify-center">
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6} md={4}>
            <div className="">
              <p className="font-marsf text-xl md:text-3xl font-normal">
                Categories
              </p>
              <p className="font-san text-[#717171] mt-5 text-[18px]">
                Pellentesque tincidunt tristique neque, eget venenatis enim
                gravida quis. Fusce at egestas libero. Cras convallis egestas
                ullamcorper. Suspendisse sed ultricies nisl, pharetra rutrum
                mauris. Vestibulum at massa dui. Morbi et purus velit.
              </p>
              <p className="font-san text-[#717171] mt-5 text-[18px]">
                Etiam tristique, justo eu condimentum efficitur, purus velit
                facilisis sem.
              </p>
            </div>
          </Grid>
          <Grid item xs={12} sm={6} md={8}>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-5 mt-10 md:mt-9 mx-40">
              {imagePaths.map((imagePath, index) => (
                <div
                  key={index}
                  className="relative transition-all duration-300 transform group hover:-translate-y-1"
                >
                  <div className="w-[160px] h-[160px] border border-solid border-gray-400 flex justify-center items-center group-hover:border-yellow-400 p-3">
                    <img
                      src={imagePath}
                      alt={`Icon ${index}`}
                      className="w-[40px] h-[40px] transition-all duration-300 group-hover:brightness-110"
                    />
                  </div>
                </div>
              ))}
            </div>
          </Grid>
        </Grid>
      </div>
      <div className="flex mt-32">
        <img
          src="https://amfissa.qodeinteractive.com/wp-content/uploads/2021/07/h1-bg-img1.jpg"
          alt=""
          className="w-300 h-[45rem]"
        />
        <div className="w-full bg-[#3c3434]">
          <main className="container w-[75%] ml-56 space-y-5 flex flex-col px-8 mt-40 ">
            <h2 className="font-marsf md:text-4xl lg:text-5xl text-white mb-2">
              We strive to make the best olive produce as verified by tasting
              experts.
            </h2>
            <p className="text-justify mr-28 font-san text-[18px] text-white">
              Award-Winning - All Natural - Authentic Olive Oils
            </p>
          </main>
        </div>
      </div>
    </div>
  );
}

export default HomeCategories;
