const { ActivityType } = require('discord.js');

module.exports = {
    name: 'ready',
    once: true,
    execute(client) {
        const activities = [
            { name: 'Supporto', type: ActivityType.Playing },
            { name: 'Sede del club di calcio', type: ActivityType.Watching },
            { name: 'Inazuma Eleven Victory Road', type: ActivityType.Competing }
        ];

        const statuses = ['idle', 'dnd'];

        let currentActivityIndex = 0;
        let currentStatusIndex = 0;

       
        let apiOverride = {
            activity: null,
            status: null,
        };

      
        function setActivityAndStatus() {
            const activity = apiOverride.activity || activities[currentActivityIndex];
            const status = apiOverride.status || statuses[currentStatusIndex];

            client.user.setPresence({
                activities: [activity],
                status: status,
            });

            if (!apiOverride.activity) {
                currentActivityIndex = (currentActivityIndex + 1) % activities.length;
            }

            if (!apiOverride.status) {
                currentStatusIndex = (currentStatusIndex + 1) % statuses.length;
            }
        }

       
        setTimeout(() => {
            setActivityAndStatus();
            console.log('\x1b[31m[ CORE ]\x1b[0m \x1b[32m%s\x1b[0m', 'Bot Activity Set Successfully ✅');
        }, 2000);

        
        setInterval(() => {
            setActivityAndStatus();
        }, 6000);

        
        client.setActivityAndStatus = (activity, status) => {
            apiOverride.activity = activity ? { name: activity.name, type: activity.type } : null;
            apiOverride.status = status || null;
            setActivityAndStatus();
        };
    },
};
