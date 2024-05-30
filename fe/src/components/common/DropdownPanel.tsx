const border = "component-border dark:component-border--dark";
const borderBottom = `border-b-[1px] ${border}`;

interface PropsType {
	top: string;
	title: string;
	contents: string[];
	imgs?: string[];
	color?: string[];
	data?: Milestone[] | Label[] | Member[]; //TODO 필터용 판낼을 따로 만들어야할듯
	handler?: (e: React.ChangeEvent<HTMLInputElement>, idx: number) => void;
	isState?: boolean;
}

//id도 데이터 담아야해서
function DropdownPanel({ top, title, contents, imgs, color, data, handler, isState }: PropsType) {
	return (
		<div
			className={`absolute ${top} w-[240px] ${border} border-[1px] rounded-xl z-20 shadow-modal dark:shadow-dark`}
		>
			<h3
				className={`${borderBottom} flex items-center cursor-default bg-grayscale.100 dark:bg-grayscale.900 rounded-t-xl`}
			>
				<span className="mx-3 my-2 text-xs text-grayscale.600 dark:text-grayscale.500">
					{title}
				</span>
			</h3>
			<ul>
				{contents &&
					contents.map((content, i) => (
						<li
							key={i}
							className={`py-2 flex items-center justify-between w-full bg-grayscale.50 dark:bg-grayscale.800 ${
								i === contents.length - 1 ? " rounded-b-xl" : borderBottom
							}`}
						>
							<input
								className="hidden peer"
								type="checkbox"
								id={content}
								onChange={(e) => handler && handler(e, i)}
							/>
							<label
								className="flex items-center w-full cursor-pointer peer-checked:font-black"
								htmlFor={content}
							>
								{imgs && (
									<img
										className="w-[20px] h-[20px] rounded-full ml-3"
										alt="userProfile"
										src={imgs[i]}
									/>
								)}
								{color && (
									<div className={`w-[20px] h-[20px] rounded-full ml-3 bg-[${color[i]}]`}></div>
								)}
								<span className="ml-2 text-grayscale.700 dark:text-grayscale.400">{content}</span>
							</label>
							{isState || (
								<label
									className="mx-3 checkbox dark:border-grayscale.400 peer-checked:after:checkbox--checked dark:peer-checked:after:checkbox--checked--dark"
									htmlFor={content}
								></label>
							)}
						</li>
					))}
			</ul>
		</div>
	);
}

export default DropdownPanel;