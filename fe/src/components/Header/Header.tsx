import { Link } from "react-router-dom";
import ProfileMenu from "./ProfileMenu/ProfileMenu";
import { ReactComponent as LogoBlack } from "../LogoBlack.svg";
import { ReactComponent as LogoWhite } from "../LogoWhite.svg";
import { useContext, useMemo, useState } from "react";
import { ThemeContext } from "../../provider/ThemeProvider";

function Header() {
	const [darkMode] = useContext(ThemeContext);
	const [isVisible, setIsVisible] = useState(false);

	const LogoComponent = useMemo(() => {
		const Logo = darkMode ? LogoWhite : LogoBlack;
		return <Logo className="cursor-pointer" />;
	}, [darkMode]);

	return (
		<div className="my-2 flex justify-between items-center">
			<Link to="/">{LogoComponent}</Link>
			<div className="relative flex justify-between items-center">
				<button className="z-10" onClick={() => setIsVisible((prev) => !prev)}>
					{/*TODO : db의 유저프로필로 변경예정 */}
					<img
						className="w-[32px] h-[32px] rounded-full"
						alt="userProfile"
						src="https://s3-alpha-sig.figma.com/img/bfa1/72b0/77fbdbfc84f8ad555402b23fb6c7a0ed?Expires=1716163200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=eI0HusP8AQJhfrYkbdft4etLT-322gDp7B7Px-jCgKq9YxT-2fFKD4o6AhzmnVaFjLWGiHP0xS~kATP~GzdJOyVdsfc4UEryn1QuF2T9PmoEdt0ZnUR7bqsSHuOReoVWy67p4Drl~meTCSGbWn8amC1-vFCT23Coy9HLU9fkNA0r3uh47-NMSV-Wx7IwUF202FHxOo027XQFyYGP9Xu56j19~mvu0d9TAlW~oHGscTheXQL5afzDdwBFrEGbMgU2Lli2QKdpkrDnjUKb0mRtqWOAVPU45~RZnFemwVP2UKq~e9Q68Q5u4zzvqrlcXbcTyHjkgYGiD6vSTPX-AlMiHA__"
					/>
				</button>
				{isVisible && <ProfileMenu setIsVisible={setIsVisible} />}
			</div>
		</div>
	);
}

export default Header;
