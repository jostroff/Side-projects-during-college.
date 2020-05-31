using Microsoft.AspNetCore.SignalR;
using System.Threading.Tasks;

namespace WebApp1.Hubs
{
    public class CanvasHub : Hub
    {
        public async Task SendMessage(int xCoord, int yCoord, string newColor)
        {
            await Clients.All.SendAsync("ReceiveMouseDown", xCoord, yCoord, newColor);
            await Clients.All.SendAsync("ReceiveMouseMove", xCoord, yCoord, newColor);
        }
    }
}