

const { SlashCommandBuilder, EmbedBuilder } = require('discord.js');
const Icons = require('../UI/Icons');
module.exports = {
    data: new SlashCommandBuilder()
        .setName('support')
        .setDescription('Support server of this Bot'),
    async execute(interaction) {
        const supportServerLink = "https://discord.gg/n9BWPAQ4Ub";
        const githubLink = "https://github.com";
        const replitLink = "https://replit.com";
        const youtubeLink = "https://www.youtube.com/@InazumAle";

        const embed = new EmbedBuilder()
            .setColor('#b300ff')
            .setAuthor({
                name: 'Support Server',
                iconURL: Icons.dotIcon,
                url: 'https://discord.gg/n9BWPAQ4Ub'
            })
            .setDescription(`➡️ **Join our Discord server for support and updates:**\n- Discord - ${supportServerLink}\n\n➡️ **Follow us on:**\n- GitHub - ${githubLink}\n- Replit - ${replitLink}\n- YouTube - ${youtubeLink}`)
            .setImage('https://tenor.com/view/inazuma-eleven-inazuma-eleven-orion-anime-gouenji-shuuya-happy-gif-25407100')
            .setTimestamp();

        await interaction.reply({ embeds: [embed] });
    },
};
