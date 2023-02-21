-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 07, 2023 at 08:00 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `estore`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_dat`
--

CREATE TABLE `admin_dat` (
  `admin_id` int(11) NOT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin_dat`
--

INSERT INTO `admin_dat` (`admin_id`, `username`, `password`) VALUES
(1, 'admin', 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `name`, `email`, `password`) VALUES
(1, 'John Smith', 'johnsmith@gmail.com', '6117377b4c95bbc55e5b4946192ee7ba'),
(2, 'Emily Davis', 'emilydavis@yahoo.com', 'd12f28ccdda87316fc6fa90be1559606'),
(3, 'Michael Brown', 'michaelbrown@hotmail.com', '6621828c112360600c84dbad23595e5c'),
(4, 'Jessica Wilson', 'jessicawilson@gmail.com', '485ce472145368d1adda9bb7c4925533'),
(5, 'Matthew Taylor', 'matthewtaylor@yahoo.com', '272689bb6ea0df69b01ec72daf410866'),
(6, 'David Anderson', 'davidanderson@hotmail.com', 'a7afa7886c32805ec9feee2e57890d77'),
(7, 'Daniel Thompson', 'danielthompson@gmail.com', '3244d4e1aa2f79531545094beef71d71'),
(8, 'Paul Gonzalez', 'paulgonzalez@yahoo.com', '509f167f46bda5f16a7a402303019e67'),
(9, 'Mark Rodriguez', 'markrodriguez@hotmail.com', '7ddf41ed8817800f81a96f35b83724c9'),
(10, 'Jennifer Lewis', 'jenniferlewis@gmail.com', '93bddf9074d67fa4f2d7750adcb452d9'),
(11, 'Elizabeth Young', 'elizabethyoung@yahoo.com', 'c190221a1cc65232f62310e737656ef2'),
(12, 'Joshua King', 'joshuaking@hotmail.com', 'a0e6c3a5034429c1c6312391ecab4769'),
(13, 'Mary Wright', 'marywright@gmail.com', 'fd79739f3d19e0263170147e9e18f079'),
(14, 'Brian Lopez', 'brianlopez@yahoo.com', 'd816e10d8207c8633dd29e9f14f61abb'),
(15, 'Kevin Hill', 'kevinhill@hotmail.com', 'ee0e10a887df9cf0fa87975e430116b6');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_number` int(11) NOT NULL,
  `customer_email` varchar(255) NOT NULL,
  `order_date` date DEFAULT NULL,
  `total_price` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `photo_path` varchar(255) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `price` decimal(10,0) NOT NULL,
  `description` text DEFAULT NULL,
  `quantity` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `photo_path`, `photo_url`, `price`, `description`, `quantity`) VALUES
(1, 'Control: Ultimate Edition', 'images/Control-Ultimate-Edition.webp', 'https://image.api.playstation.com/vulcan/img/rnd/202009/1409/LOjeGQPXg7C1yoMmhsKYOwfa.png?w=440&thumb=false', '40', 'A corruptive presence has invaded the Federal Bureau of Control…Only you have the power to stop it. The world is now your weapon in an epic fight to annihilate an ominous enemy through deep and unpredictable environments. Containment has failed, humanity is at stake. Will you regain control?', 19),
(2, 'Mortal Kombat 11', 'images/Mortal-Kombat-11.webp', 'https://image.api.playstation.com/vulcan/ap/rnd/202009/0123/bF1qmEL5RM6aMfL0oLcxRe8B.png?w=440&thumb=false', '50', 'The all new Custom Character Variations give you unprecedented control of your fighters to make them your own. The new graphics engine showcases every skull-shattering, eye-popping moment, bringing you so close to the fight you can feel it. Featuring a roster of new and returning Klassic Fighters, Mortal Kombats best-in-class cinematic story mode continues the epic saga over 25 years in the making.', 51),
(3, 'Shadow Warrior 3', 'images/Shadow-Warrior-3.webp', 'https://image.api.playstation.com/vulcan/ap/rnd/202107/2401/WdFb9QYf8U3oj8NHMsMfWxe5.png?w=440&thumb=false', '40', 'Fallen corporate shogun Lo Wang and his former employer turned nemesis turned sidekick Orochi Zilla embark on an improbable mission to recapture an ancient dragon they unwillingly unleashed from its eternal prison. Armed with a punishing mix of blades and bullets, Lo Wang must traverse uncharted parts of the world to track down the dark beast and push the apocalypse back yet again. All it will take is the mask of a dead god, a dragons egg, a touch of magic, and enough firepower to hold off the impending cataclysm.', 63),
(4, 'Dragon Star Varnir', 'images/Dragon-Star-Varnir.jpg', 'https://image.api.playstation.com/cdn/UP0031/CUSA14503_00/d0WHFQQ9fWRCi2CsBy2c6FtulKft5pEU.png?w=440&thumb=false', '40', 'Awaken Your Inner Dragon – When attacking an enemy during battle, players fill up their Dragon Gauge. Once maxed, they can transform and harness the power of dragons, drastically increasing their armor and unlocking abilities! Devour Hour – Once an enemy dragon is weakened, players can utilize the Devour skill to obtain a skill tree unique to that dragon. Players can explore different dragon skill trees and combinations! Madness or Riches? – Three witches depend on you to bring them dragons blood as food. Starve them, and they go mad. Overfeed them, and they become a dragon! Will you keep them alive or sacrifice them to obtain rare items? Be wary, each choice you make can change your ending.', 50),
(5, 'Outer Wilds', 'images/Outer-Wilds.webp', 'https://image.api.playstation.com/vulcan/ap/rnd/202208/1623/Zofebh60Ue7Zt5sC10UAtU3D.png?w=440&thumb=false', '25', 'Outer Wilds is an open world mystery about a solar system trapped in an endless time loop. Welcome to the Space Program! Youre the newest recruit of Outer Wilds Ventures, a fledgling space program searching for answers in a strange, constantly evolving solar system.', 27),
(6, 'Kingdom Two Crowns', 'images/Kingdom-Two-Crowns.webp', 'https://image.api.playstation.com/vulcan/ap/rnd/202111/2310/S8MdQILD9cx2ffeNhqNONc93.png?w=440&thumb=false', '20', 'Kingdom Two Crowns is a side-scrolling micro strategy game with a minimalist feel wrapped in a beautiful, modern pixel art aesthetic. Play the role of a monarch atop their steed and recruit loyal subjects, build your kingdom and protect it from the greedy creatures looking to steal your coins and crown. In the brand-new campaign mode, monarchs must now work to build a kingdom that stands over time until finding a way to defeat the Greed for good. Explore the environments to discover new mounts and secrets hidden in the deep.', 31),
(7, 'Ghostrunner', 'images/Ghostrunner.webp', 'https://image.api.playstation.com/vulcan/ap/rnd/202108/2600/hdoOAnRlmDSm5kCcgggrXnIT.png?w=440&thumb=false', '30', 'Ghostrunner is a hardcore FPP slasher packed with lightning-fast action, set in a grim, cyberpunk megastructure. Climb Dharma Tower, humanitys last shelter, after a world-ending cataclysm. Make your way up from the bottom to the top, confront the tyrannical Keymaster, and take your revenge. The streets of this tower city are full of violence. Mara the Keymaster rules with an iron fist and little regard for human life.', 57),
(8, 'Life is Strange', 'images/Life-is-Strange.webp', 'https://image.api.playstation.com/gs2-sec/acpkgo/prod/CUSA01442_00/30/i_8d29bc9dc65290ef70267e306c51a79b541fe8c52b7532b4a58927fcbe302aa0/i/icon0.png?w=440&thumb=false', '20', 'Life is Strange is an award-winning and critically acclaimed episodic adventure game that allows the player to rewind time and affect the past, present and future. Follow the story of Max Caulfield, a photography senior who discovers she can rewind time while saving her best friend Chloe Price. The pair soon find themselves investigating the mysterious disappearance of fellow student Rachel Amber, uncovering a dark side to life in Arcadia Bay. Meanwhile, Max must quickly learn that changing the past can sometimes lead to a devastating future.', 91),
(9, 'Devil May Cry 5', 'images/Devil-May-Cry-5.jpg', 'https://image.api.playstation.com/cdn/UP0102/CUSA08216_00/nxnwRA9ePhYHVjnfn406ZXw1Ug0ddELA.png?w=440&thumb=false', '20', 'The ultimate Devil Hunter is back in style, in the game action fans have been waiting for. A brand new entry in the legendary action series, Devil May Cry 5 brings together its signature blend of high-octane action and otherworldly original characters with the latest Capcom gaming technology to deliver a graphically groundbreaking action-adventure masterpiece.', 14),
(10, 'Mortal Shell', 'images/Mortal-Shell.webp', 'https://image.api.playstation.com/vulcan/ap/rnd/202008/0109/F5Wvzwr4VFVlonLj4wtjYkDx.png?w=440&thumb=false', '30', 'Mortal Shell is a ruthless and deep action-RPG that tests your sanity and resilience in a shattered world. As the remains of humanity wither and rot, zealous foes fester in the ruins. They spare no mercy, with survival demanding superior awareness, precision, and instincts. Track down hidden sanctums of devout followers and discover your true purpose.', 5),
(11, 'Spiritfarer: Farewell Edition', 'images/Spiritfarer-Farewell-Edition.webp', 'https://image.api.playstation.com/vulcan/img/rnd/202112/1100/oofNmdeDcfWwSU4XUOFSazSc.png?w=440&thumb=false', '30', 'Spiritfarer® is a cozy management game about dying. You play Stella, ferrymaster to the deceased, a Spiritfarer. Build a boat to explore the world, then befriend and care for spirits before finally releasing them into the afterlife. Farm, mine, fish, harvest, cook, and craft your way across mystical seas. Join the adventure as Daffodil the cat, in two-player cooperative play. Spend relaxing quality time with your spirit passengers, create lasting memories, and, ultimately, learn how to say goodbye to your cherished friends.', 20),
(12, 'OMNO', 'images/OMNO.webp', 'https://image.api.playstation.com/vulcan/ap/rnd/202105/2614/XH1B1VNaFirvpYxYWpFlLuQN.png?w=440&thumb=false', '19', 'A single-player journey of discovery through an ancient world of wonders. Full of puzzles, secrets and obstacles to overcome, where the power of a lost civilisation will carry you through forests, deserts and tundras - even to the clouds.AN EMOTIONAL JOURNEY A BEAUTIFUL MYSTERIOUS WORLD MEET FANTASTICAL CREATURES', 8),
(13, 'JETT: The Far Shore', 'images/JETT-The-Far-Shore.webp', 'https://image.api.playstation.com/vulcan/ap/rnd/202107/1213/iFQoOsGMY1HjkDFOWijVGJaz.png?w=440&thumb=false', '18', 'JETT : THE FAR SHORE invites you on an interstellar trip to carve out a future for a people haunted by oblivion in this cinematic action adventure. As scout Mei, be the first to deploy to a mythic ocean planet. Take the helm of a jett and explore a vast unknown - skim low over waves, roar up pristine coastlines and carve through otherworldly woods. Adapt to an intricate, systemic open world and persevere through adversity alongside an intimate ensemble cast in this story of courage, wonder and regret.', 58),
(14, 'The Medium', 'images/The-Medium.webp', 'https://image.api.playstation.com/vulcan/ap/rnd/202105/1813/DV0IXPHtc7JRTLoxsKAg0sJh.png?w=440&thumb=false', '50', 'Discover a dark mystery only a medium can solve. Explore the real world and the spirit world at the same time. Use your psychic abilities to solve puzzles spanning both worlds, uncover deeply disturbing secrets, and survive encounters with The Maw - a monster born from an unspeakable tragedy.. The Medium is a third-person psychological horror game that features innovative dual-reality gameplay and an original soundtrack co-composed by Arkadiusz Reikowski and Akira Yamaoka. Get even more immersed in its uncanny atmosphere thanks to the extensive support for the unique features of your DualSense controller.', 18),
(15, 'DRAGON QUEST XI S: Echoes of an Elusive Age – Definitive Edition', 'images/DRAGON-QUEST.webp', 'https://image.api.playstation.com/vulcan/ap/rnd/202007/1617/ztkfmNFcuZBBkwsLUelKzgqW.png?w=440&thumb=false', '40', 'The Definitive Edition includes all the content from the original release of the acclaimed DRAGON QUEST XI, and adds extra character-specific scenarios, the choice of playing with the original soundtrack or a grand orchestral version of the music, the ability to switch between 2D and 3D graphic modes, a Japanese voice-acting option, and much more!', 41),
(16, 'Chorus', 'images/Chorus.webp', 'https://image.api.playstation.com/vulcan/ap/rnd/202109/0817/yLhQ4TPXkilrPeaJTvs7m7JW.png?w=440&thumb=false', '40', 'Take control of Nara on a quest to destroy the dark cult that created her. Unlock devastating weapons and mind-bending abilities in a true evolution of the space-combat shooter. Along with Forsaken, her sentient starfighter, explore ancient temples, engage in exhilarating zero-g combat, and venture beyond our waking reality.', 54);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_dat`
--
ALTER TABLE `admin_dat`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_number`),
  ADD KEY `customer_email` (`customer_email`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_dat`
--
ALTER TABLE `admin_dat`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_number` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_email`) REFERENCES `customer` (`email`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
